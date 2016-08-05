package org.michaelliu.rpc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by michael on 2016/8/4.
 */
public class RmiProviderInit {

    private static final Log log = LogFactory.getLog(RmiProviderInit.class);

    private static void init() {
        log.info("Rmi prepares to start...");
        new ClassPathXmlApplicationContext("classpath:config/rmi-provider.xml");
        log.info("Rmi has started...");
    }

    public static void main(String[] args) {
        init(); //won't break unless you force it stop.
    }

}
