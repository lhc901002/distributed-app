package org.michaelliu.rpc.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.rpc.service.AccountService;
import org.michaelliu.vo.AccountVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Michael on 8/4/16.
 */
public class TestRmi {

    private static final Log log = LogFactory.getLog(TestRmi.class);

    public static void testGetAccountService() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:config/rmi-consumer.xml");
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
        AccountVo account = accountService.findById(1l);
        log.info("Rmi AccountService returns: " + JSON.toJSONString(account));
        List<AccountVo> accountList = accountService.findAll();
        log.info("Rmi AccountService returns: " + JSON.toJSONString(accountList));
        accountList = accountService.findByName("M");
        log.info("Rmi AccountService returns: " + JSON.toJSONString(accountList));
    }

    public static void testSaveAccountService() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:config/rmi-consumer.xml");
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
        Long accountId = null;
        try {
            accountId = accountService.saveOrUpdateSelective(new AccountVo.Builder().setName("hello")
                    .setBalance(300).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Rmi AccountService returns: " + accountId);
    }

    public static void main(String[] args) {
//        testGetAccountService();
        testSaveAccountService();
    }

}
