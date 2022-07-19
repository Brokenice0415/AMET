package org.mpisws.hitmc.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.mpisws.hitmc.api.configuration.SchedulerConfigurationException;
import org.mpisws.hitmc.server.TestingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ZookeeperMain {

    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperMain.class);

    public static void main(final String[] args) {
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ZookeeperSpringConfig.class);
        final TestingService testingService = applicationContext.getBean(TestingService.class);

        try {
            testingService.loadConfig(args);
            testingService.initRemote();
//            testingService.start();
            testingService.startWithExternalModel();
            System.exit(0);
        } catch (final SchedulerConfigurationException e) {
            LOG.error("Error while reading configuration.", e);
        } catch (final IOException e) {
            LOG.error("IO exception", e);
        }
    }

}