package org.michaelliu.rpc.rmi.test;

import com.alibaba.fastjson.JSON;
import org.michaelliu.entity.Account;
import org.michaelliu.rpc.service.rmi.RmiAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Michael on 8/4/16.
 */
public class TestRmi {

    private static void testAccountService() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:config/applicationContext.xml");
        RmiAccountService accountService = appContext.getBean("rmiAccountService", RmiAccountService.class);
        List<Account> accountList = accountService.findAll();
        System.out.println("AccountService received: " + JSON.toJSONString(accountList));
    }

    public static void main(String[] args) {

    }

}
