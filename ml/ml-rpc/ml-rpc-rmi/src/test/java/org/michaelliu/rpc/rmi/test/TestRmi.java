package org.michaelliu.rpc.rmi.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.entity.Account;
import org.michaelliu.rpc.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Michael on 8/4/16.
 */
public class TestRmi {

    private static final Log log = LogFactory.getLog(TestRmi.class);

    private static void testAccountService() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:config/rmi-consumer.xml");
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
        Account account = accountService.findById(123l);
        log.info("Rmi AccountService returns: " + JSON.toJSONString(account));
        List<Account> accountList = accountService.findAll();
        log.info("Rmi AccountService returns: " + JSON.toJSONString(accountList));
    }

    public static void main(String[] args) {
        testAccountService();
    }

}