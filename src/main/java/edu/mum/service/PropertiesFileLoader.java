package edu.mum.service;

import edu.mum.dao.DaoLayer;
import edu.mum.domain.Autoactivityrunsetting;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.logging.Level;

/**
 *
 * @author Gilbert Ndenzi
 */
@Component("asyncJob")
public class PropertiesFileLoader implements AutomaticActivityManagerService {

    @Autowired
    DaoLayer daoLayer;

    /**
     *
     * @throws IOException
     */
    @Override
    public void registerServicesInitialInfo() throws IOException {
        Properties prop = new Properties();
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream in = classLoader.getResourceAsStream("createdservices.properties");
        List<String> registeredservices = (List<String>) daoLayer.fetchRecord(Autoactivityrunsetting.class, new String[]{"activityname"}, "", new String[]{}, new Object[]{});
        try {
            prop.load(in);
            Enumeration e = prop.propertyNames();
            while (e.hasMoreElements()) {
                if (registeredservices != null) {
                    String key = (String) e.nextElement();
                    if (registeredservices.contains(prop.getProperty(key))) {
                        continue;
                    }
                    if (key.endsWith("description") || key.endsWith("beanname")) {
                        continue;
                    }
                    Autoactivityrunsetting autoactivityrunsetting = new Autoactivityrunsetting(prop.getProperty(key), prop.getProperty(key + "_description"), prop.getProperty(key + "_beanname"));
                    autoactivityrunsetting.setStarttime(new Date());
                    autoactivityrunsetting.setAdded(Boolean.FALSE);
                    daoLayer.saveRecord(autoactivityrunsetting);
                } else {
                    String key = (String) e.nextElement();
                    if (key.endsWith("description") || key.endsWith("beanname")) {
                        continue;
                    }
                    Autoactivityrunsetting autoactivityrunsetting = new Autoactivityrunsetting(prop.getProperty(key), prop.getProperty(key + "_description"), prop.getProperty(key + "_beanname"));
                    autoactivityrunsetting.setStarttime(new Date());
                    autoactivityrunsetting.setAdded(Boolean.FALSE);
                    daoLayer.saveRecord(autoactivityrunsetting);
                }
                System.out.println("----------------------------------------------------------------------------------------------");
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(PropertiesFileLoader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            in.close();
        }
    }

    private static final Logger LOG = Logger.getLogger(PropertiesFileLoader.class.getName());
}
