/*
 * Copyright (C) 2016 gilberto
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.mum.service;
import edu.mum.dao.DaoLayer;
import edu.mum.domain.Services;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import javax.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author gilberto
 */
@Component
public class AutomatedServicesLauncher implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    DaoLayer daoLayer;
    
    @Autowired
    private ApplicationContext appContextx;
    
    @Autowired
    private TaskScheduler taskScheduler;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {    
        List<Object[]> servicesToSchedule = (List<Object[]>) daoLayer.fetchRecord(Services.class, 
                    new String[]{"serviceid", "servicename", "status", "description", "lastruntime",
                        "datecreated", "startondemand", "startonstartup", "startingtimepattern", 
                        "autoactivityrunsetting.beanname"}, "", new String[]{}, new Object[]{});
        
        if (servicesToSchedule!=null) {
            for (Object[] serviceToSchedule : servicesToSchedule) {
                String cronexpression = (String) serviceToSchedule[8];
                String beanname = (String) serviceToSchedule[9];
                String servicename = (String) serviceToSchedule[1];
                Boolean interrupted = (Boolean) serviceToSchedule[2];
                Boolean startonstartup = (Boolean) serviceToSchedule[7];
                Integer serviceid = (Integer) serviceToSchedule[0];
                
                if (serviceToSchedule[2] != null) {
                    if (interrupted) {
                        daoLayer.updateRecord(Services.class, new String[]{"interrupted"},
                                new Object[]{Boolean.TRUE},
                                "serviceid", serviceid);
                    } else {
                        daoLayer.updateRecord(Services.class, new String[]{"interrupted"},
                                new Object[]{Boolean.FALSE},
                                "serviceid", serviceid);
                    }
                }
                if (startonstartup && appContextx.getBean(beanname)!=null) {
                    System.out.println("##################################Automated Service " + servicename + " has been loaded and scheduled with its Runtime settings##############################");
                    CronTrigger trigger = new CronTrigger(cronexpression);
                    ScheduledFuture<?> scedulefuture2 = taskScheduler.schedule((Runnable) appContextx.getBean(beanname), trigger);
                }
            }
        }
    }   
    
    @PostConstruct
    public void executeAfterServerStart(){
        System.out.println(".....................Detected Start Shutdown........................");
    }
    
    @PreDestroy
    public void executeBeforeServerShutdown(){
        System.out.println(".....................Detected Server Shutdown........................");
    }
}
