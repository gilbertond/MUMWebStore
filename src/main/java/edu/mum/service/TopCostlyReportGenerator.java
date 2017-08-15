/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.service;

import edu.mum.dao.DaoLayer;
import edu.mum.dao.ProductRepository;
//import edu.mum.dao.ProductRepositoryPaging;
import edu.mum.dao.ServiceCrudService;
import edu.mum.domain.Product;
import edu.mum.domain.Services;
import edu.mum.domain.Topcostlyproduct;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

/**
 *
 * @author GNdenzi
 */
@Component("serviceManager")
public class TopCostlyReportGenerator implements Runnable {

    static Properties props;
    @Autowired
    DaoLayer daoLayer;

    @Autowired
    ServiceCrudService crudService;

//    @Autowired
//    ProductRepositoryPaging productRepository;
    
    @Autowired
    ProductRepository productRepositoryx;

    Integer autoid = 0;
    Boolean startonstartup = true;
    String cronexpression = "";
    boolean status = false;
    boolean abortExec = false;
    int nowT;
    int startT;

    @Override
    public void run() {
        try {
            System.out.println("execution here...");
            List<Object[]> service = (List<Object[]>) daoLayer.fetchRecord(Services.class,
                    new String[]{"autoactivityrunsetting.autoactivityrunsettingid", "startingtimepattern", "startonstartup"},
                    "WHERE r.autoactivityrunsetting.beanname=:beanname", new String[]{"beanname"}, new Object[]{"serviceManager"});
            if (service == null) {
                status = false;
            }
            autoid = (Integer) service.get(0)[0];
            cronexpression = (String) service.get(0)[1];
            startonstartup = (Boolean) service.get(0)[2];

            daoLayer.updateRecord(Services.class, new String[]{"startingtime", "status", "completed", "interrupted"},
                    new Object[]{new Date(), Boolean.TRUE, Boolean.FALSE, Boolean.FALSE},
                    "autoactivityrunsetting", autoid);

            //Code section to call the method for initiating the file creation
            boolean ended = generateReport();

            daoLayer.updateRecord(Services.class, new String[]{"completed"}, new Object[]{Boolean.TRUE},
                    "autoactivityrunsetting", autoid);
            status = true;

            System.out.println("End...");
        } catch (Exception e) {
            e.printStackTrace();
            if (!abortExec) {
                daoLayer.updateRecord(Services.class, new String[]{"completed", "status"}, new Object[]{Boolean.FALSE, Boolean.FALSE},
                        "autoactivityrunsetting", autoid);
                status = false;
            }
        } finally {
            if (!abortExec) {
                if (startonstartup) {
                    CronSequenceGenerator generator = new CronSequenceGenerator(cronexpression, TimeZone.getDefault());
                    final Date nextExecutionDate = generator.next(new Date());

                    daoLayer.updateRecord(Services.class, new String[]{"endingtime", "lastruntime", "status", "nextruntime"},
                            new Object[]{new Date(), new Date(), Boolean.FALSE, nextExecutionDate},
                            "autoactivityrunsetting", autoid);

                } else {
                    daoLayer.updateRecord(Services.class, new String[]{"endingtime", "lastruntime", "status"},
                            new Object[]{new Date(), new Date(), Boolean.FALSE},
                            "autoactivityrunsetting", autoid);
                }
            }
        }
    }

    public boolean generateReport() throws InterruptedException, IOException {
        boolean ended = false;
        props = new Properties();
        try {
            List<Product> products = productRepositoryx.findAll();//productRepository.findTop10ByCost();
            if (products != null) {
                for (Product product : products) {
//                    List<Topcostlyproduct> currentInReport = crudService.find(product.getProductId().intValue());
                    Topcostlyproduct topcostlyproduct = new Topcostlyproduct(new Date(), product, product.getCost());
                    daoLayer.saveRecord(topcostlyproduct);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ended;
    }
}
