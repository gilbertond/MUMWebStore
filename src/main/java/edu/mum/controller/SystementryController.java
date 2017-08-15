/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.controller;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import static com.cronutils.model.CronType.QUARTZ;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import edu.mum.domain.Autoactivityrunsetting;
import edu.mum.domain.Services;
import edu.mum.service.ServiceLayer;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Gilbert Ndenzi
 */
@Controller
public class SystementryController {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    ServiceLayer serviceLayer;

    @Autowired
    private ApplicationContext appContextx;

    @Autowired
    private TaskScheduler taskScheduler;

    @RequestMapping(value = "/manageScheduleTasks")
    public ModelAndView manageScheduleTasks(HttpServletRequest request, @RequestParam("ref") Boolean ref, Principal principal) {
        Map<String, Object> model = new HashMap<String, Object>();
        if(principal == null){
            model.put("redir", true);
        }
        try {
            List<Object[]> services = (List<Object[]>) serviceLayer.fetchRecord(Services.class,
                    new String[]{"serviceid", "servicename", "status", "description", "lastruntime", "datecreated", "startondemand",
                        "startonstartup", "startingtimepattern", "startingtime", "endingtime", "completed", "crondescription",
                        "autoactivityrunsetting.beanname", "interrupted"}, "", new String[]{}, new Object[]{});
            model.put("services", services);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ModelAndView("errorPage");
        }
        if (ref) {
            return new ModelAndView("TasksSchedule/tasksList", "model", model);
        }
        return new ModelAndView("TasksSchedule/manageScheduleTasks", "model", model);
    }

    @RequestMapping(value = "/addServiceNSchedule")
    public ModelAndView addServiceNSchedule(HttpServletRequest request, Principal principal) {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            List<Object[]> services = (List<Object[]>) serviceLayer.fetchRecord(Autoactivityrunsetting.class,
                    new String[]{"autoactivityrunsettingid", "activityname", "added",
                        "starttime", "description", "beanname",}, "WHERE r.autoactivityrunsettingid NOT IN (SELECT s.autoactivityrunsetting.autoactivityrunsettingid "
                    + "FROM Services s)", new String[]{}, new Object[]{});
            model.put("services", services);

            System.out.println("Here");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ModelAndView("TasksSchedule/addServiceNSchedule", "model", model);
    }

    @RequestMapping(value = "/saveScheduledService", method = RequestMethod.POST)
    public ModelAndView saveScheduledService(HttpServletRequest request, Principal principal) throws NoSuchBeanDefinitionException {
        Map<String, Object> model = new HashMap<>();
        String bean = "", service = "", cronexpression = "";
        try {
            String servicename = request.getParameter("servicename");
            String description = request.getParameter("description");
            String beanname = request.getParameter("beanname");
            Integer autoid = Integer.parseInt(request.getParameter("autoid"));

            bean = beanname;
            service = servicename;

            int startoncreation = request.getParameter("startoncreation") == null ? 0 : Integer.parseInt(request.getParameter("startoncreation"));
            cronexpression = request.getParameter("cronexpression");

            Services services = new Services();
            services.setServicename(servicename);
            services.setDescription(description);
            services.setDatecreated(new Date());
            services.setInterrupted(Boolean.FALSE);
            services.setAutoactivityrunsetting(new Autoactivityrunsetting(autoid));
            CronTrigger trigger = new CronTrigger(cronexpression);

            CronSequenceGenerator generator = new CronSequenceGenerator(cronexpression, TimeZone.getTimeZone("Africa/Kampala"));
            final Date nextExecutionDate = generator.next(new Date());
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ); //CronDefinitionRegistry.instance().retrieve(QUARTZ);

            //create a parser based on provided definition
            CronParser parser = new CronParser(cronDefinition);
            Cron quartzCron = parser.parse(cronexpression);

            //create a descriptor for a specific Locale
            CronDescriptor descriptor = CronDescriptor.instance(Locale.ENGLISH);
            //parse some expression and ask descriptor for description
            String descriptionx = descriptor.describe(quartzCron);
            services.setNextruntime(nextExecutionDate);

            if (startoncreation == 1) {
                services.setStartondemand(Boolean.TRUE);
                services.setStartonstartup(Boolean.FALSE);

                //Run Service for a manual service
                services.setCrondescription("Run manually on demand");
            } else {
                services.setCrondescription(descriptionx);
                services.setStartondemand(Boolean.FALSE);
                services.setStartonstartup(Boolean.TRUE);
                services.setStartingtimepattern(cronexpression);
            }
            if (appContextx.getBean(beanname) == null) {
                model.put("saved", false);
                model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been saved, there is no registered service[Bean: <strong>" + bean + "</strong> missing]");

                return new ModelAndView("TasksSchedule/saveSuccess", "model", model);
            }

            if (startoncreation == 1) {
                ScheduledFuture<?> scedulefuture2 = taskScheduler.schedule((Runnable) appContextx.getBean(beanname), new Date());
            } else {
                ScheduledFuture<?> scedulefuture2 = taskScheduler.schedule((Runnable) appContextx.getBean(beanname), trigger);//taskScheduler.schedule(worker, new Date());
            }
            //Save scheduled service
            serviceLayer.saveRecord(services);
            serviceLayer.updateRecord(Autoactivityrunsetting.class, new String[]{"added"},
                    new Object[]{Boolean.TRUE}, "autoactivityrunsettingid", autoid);
            boolean saved = services.getServiceid() != null;
            if (saved) {
                model.put("message", "Your Service <strong><u>" + servicename + "</ul></strong> has been saved and scheduled successfully");
            } else {
                model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been saved, [<strong> contact your admin</strong>]");
            }

            model.put("saved", saved);
        } catch (NumberFormatException ex) {
            model.put("saved", false);
            model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been saved, the cron expresion [<strong>" + cronexpression + "</strong> is wrong]");

        } catch (BeansException ex) {
            model.put("saved", false);
            model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been saved, there is no registered service[Bean: <strong>" + bean + "</strong> missing]");

        }
        return new ModelAndView("TasksSchedule/saveSuccess", "model", model);
    }

    @RequestMapping(value = "/runManualService", method = RequestMethod.GET)
    public ModelAndView runManualService(HttpServletRequest request,
            Principal principal,
            @RequestParam("id") Integer id,
            @RequestParam("bean") String beanname,
            @RequestParam("name") String servicename) throws NoSuchBeanDefinitionException {
        Map<String, Object> model = new HashMap<String, Object>();
        String bean = "", service = "";
        try {

            bean = beanname;
            service = servicename;

            if (appContextx.getBean(beanname) == null) {
                model.put("saved", false);
                model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been saved, there is no registered service[Bean: <strong>" + bean + "</strong> missing]");

                return new ModelAndView("TasksSchedule/saveSuccess", "model", model);
            }
            ScheduledFuture<?> scedulefuture2 = taskScheduler.schedule((Runnable) appContextx.getBean(beanname), new Date());

            model.put("message", "Your Service <strong><u>" + servicename + "</ul></strong> has been triggered successfully");
            model.put("saved", true);
        } catch (NumberFormatException ex) {
            model.put("saved", false);
            model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been triggered, there is no registered service[Bean: <strong>" + bean + "</strong> missing]");
//            return new ModelAndView("errorPage");
        } catch (BeansException ex) {
            model.put("saved", false);
            model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been triggered, there is no registered service[Bean: <strong>" + bean + "</strong> missing]");
//            return new ModelAndView("errorPage");
        }
        return new ModelAndView("TasksSchedule/saveSuccess", "model", model);
    }

    @RequestMapping(value = "/manageService")
    public ModelAndView manageService(HttpServletRequest request,
            @RequestParam("id") Integer id,
            @RequestParam("task") String task,
            Principal principal) {
        Map<String, Object> model = new HashMap<String, Object>();
        boolean abortExec = false;
        String servicename = "";
        String beanname = "";
        try {
            Object[] service = (Object[]) serviceLayer.fetchRecord(Services.class,
                    new String[]{"serviceid", "servicename", "status",
                        "description", "lastruntime", "datecreated", "startondemand", "startonstartup", "startingtimepattern",
                        "startingtime", "endingtime", "completed", "crondescription", "autoactivityrunsetting.beanname",
                        "autoactivityrunsetting.autoactivityrunsettingid"},
                    "WHERE r.serviceid=:serviceid", new String[]{"serviceid"}, new Object[]{id}).get(0);
            servicename = (String) service[1];
            beanname = (String) service[13];
            List<Object[]> services = (List<Object[]>) serviceLayer.fetchRecord(Autoactivityrunsetting.class,
                    new String[]{"autoactivityrunsettingid", "activityname", "added",
                        "starttime", "description", "beanname"}, "WHERE r.autoactivityrunsettingid NOT IN "
                    + "(SELECT s.autoactivityrunsetting.autoactivityrunsettingid "
                    + "FROM Services s) OR r.autoactivityrunsettingid IN "
                    + "(SELECT s.autoactivityrunsetting.autoactivityrunsettingid "
                    + "FROM Services s WHERE s.serviceid=:serviceid)", new String[]{"serviceid"}, new Object[]{id});

            if (task.equals("delete")) {
                int delete = serviceLayer.deleteRecord(Services.class, "serviceid", id);
                if (delete > 0) {
                    ScheduledFuture<?> scedulefuture2 = taskScheduler.scheduleAtFixedRate((Runnable) appContextx.getBean(beanname), 10000);
                    scedulefuture2.cancel(true);
                    model.put("saved", true);
                    model.put("message", "Your Service <strong><u>" + servicename + "</ul></strong> has been deleted successfully");
                } else {
                    model.put("saved", false);
                    model.put("message", "Your Service <strong><u>" + servicename + "</ul></strong> has not been deleted. Contact Admin");
                }
                return new ModelAndView("TasksSchedule/saveSuccess", "model", model);
            }

            model.put("services", services);
            model.put("service", service);

        } catch (Exception ex) {
            ex.printStackTrace();
            model.put("saved", false);
            model.put("message", "Your Service <strong><u>" + servicename + "</ul></strong> has not been deleted. Contact Admin");
            return new ModelAndView("TasksSchedule/saveSuccess", "model", model);
        }
        return new ModelAndView("TasksSchedule/updateServiceNSchedule", "model", model);
    }

    @RequestMapping(value = "/updateScheduledService", method = RequestMethod.POST)
    public ModelAndView updateScheduledService(HttpServletRequest request, Principal principal) throws NoSuchBeanDefinitionException {
        Map<String, Object> model = new HashMap<String, Object>();
        String bean = "", service = "";
        String cronexpression = "";
        try {
            String servicename = request.getParameter("servicename");
            String description = request.getParameter("description");
            String beanname = request.getParameter("beanname");
            Integer autoid = Integer.parseInt(request.getParameter("autoid"));
            Integer serviceid = Integer.parseInt(request.getParameter("serviceid"));

            bean = beanname;
            service = servicename;

            int startoncreation = request.getParameter("startoncreation") == null ? 0 : Integer.parseInt(request.getParameter("startoncreation"));
            cronexpression = request.getParameter("cronexpression");
            System.out.println("********" + cronexpression + "*********");
            Services services = new Services();
            services.setServicename(servicename);
            services.setDescription(description);
            services.setDatechanged(new Date());
            services.setAutoactivityrunsetting(new Autoactivityrunsetting(autoid));
            CronTrigger trigger = new CronTrigger(cronexpression);

            CronSequenceGenerator generator = new CronSequenceGenerator(cronexpression, TimeZone.getTimeZone("Africa/Kampala"));
            final Date nextExecutionDate = generator.next(new Date());
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ); //CronDefinitionRegistry.instance().retrieve(QUARTZ);

            //create a parser based on provided definition
            CronParser parser = new CronParser(cronDefinition);
            Cron quartzCron = parser.parse(cronexpression);

            //create a descriptor for a specific Locale
            CronDescriptor descriptor = CronDescriptor.instance(Locale.ENGLISH);
            //parse some expression and ask descriptor for description
            String descriptionx = descriptor.describe(quartzCron);
            services.setNextruntime(nextExecutionDate);

            if (startoncreation == 1) {
                services.setStartondemand(Boolean.TRUE);
                services.setStartonstartup(Boolean.FALSE);

                //Run Service for a manual service
                services.setCrondescription("Run manually on demand");
                services.setStartingtimepattern("");
            } else {
                services.setCrondescription(descriptionx);
                services.setStartondemand(Boolean.FALSE);
                services.setStartonstartup(Boolean.TRUE);
                services.setStartingtimepattern(cronexpression);
            }

            //Update scheduled service
            serviceLayer.updateRecord(Services.class,
                    new String[]{"servicename", "description", "datechanged", "autoactivityrunsetting",
                        "nextruntime", "startondemand", "startonstartup", "crondescription", "startingtimepattern"},
                    new Object[]{services.getServicename(), services.getDescription(), services.getDatechanged(),
                        services.getAutoactivityrunsetting().getAutoactivityrunsettingid(), services.getNextruntime(),
                        services.getStartondemand(), services.getStartonstartup(), services.getCrondescription(), services.getStartingtimepattern()},
                    "serviceid", serviceid);

            if (startoncreation == 1) {
                ScheduledFuture<?> scedulefuture2 = taskScheduler.schedule((Runnable) appContextx.getBean(beanname), new Date());
            } else {
                ScheduledFuture<?> scedulefuture2 = taskScheduler.schedule((Runnable) appContextx.getBean(beanname), trigger);//taskScheduler.schedule(worker, new Date());
            }

            model.put("message", "Your Service <strong><u>" + servicename + "</ul></strong> has been updated and scheduled successfully");
            model.put("saved", true);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            model.put("saved", false);
            model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been updated, the cron expresion [<strong>" + cronexpression + "</strong> is wrong]");
//            return new ModelAndView("errorPage");
        } catch (BeansException ex) {
            model.put("saved", false);
            model.put("message", "Your Service <strong><u>" + service + "</u></strong> has not been updated, there is no registered service[Bean: <strong>" + bean + "</strong> missing]");
//            return new ModelAndView("errorPage");
        }
        return new ModelAndView("TasksSchedule/saveSuccess", "model", model);
    }
}
