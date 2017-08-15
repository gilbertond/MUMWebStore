package edu.mum.service;

//import org.apache.log4j.Logger;
import edu.mum.dao.IUserCrudRepositoryService;
import java.io.IOException;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
/**
 *
 * @author Gilbert Ndenzi
 */
@Service
public class SchedulerService {

    @SuppressWarnings("ProtectedField")
    protected static Log logger = LogFactory.getLog("service");
    
    //FOR JOBS THAT ARE DEPENDENT ON EACH OTHER.
    @Autowired
    @Qualifier("asyncJob")
    private AutomaticActivityManagerService automaticActivityManagerService;

    @Scheduled(fixedRate=(10000))
    public void loadCreatedServices() throws IOException {
        logger.info("......Loading Newly Created Services from a Properties file.....");
        
        automaticActivityManagerService.registerServicesInitialInfo();        
        logger.info("-------End schedule-----------");
    }
    
    private static final Logger LOG = Logger.getLogger(SchedulerService.class.getName());
}
