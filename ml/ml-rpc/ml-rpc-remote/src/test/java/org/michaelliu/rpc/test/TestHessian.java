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
 * Created by michael on 2016/8/4.
 */
public class TestHessian {

    private static final Log log = LogFactory.getLog(TestHessian.class);

    private static void testGetAccountService() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:config/hessian-consumer.xml");
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
        AccountVo account = accountService.findById(123l);
        System.out.println("Hessian AccountService returns: " + JSON.toJSONString(account));
        List<AccountVo> accountList = accountService.findAll();
        System.out.println("Hessian AccountService returns: " + JSON.toJSONString(accountList));
    }

    public static void testSaveAccountService() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:config/hessian-consumer.xml");
        AccountService accountService = appContext.getBean("accountService", AccountService.class);
        Long accountId = null;
        try {
            accountId = accountService.saveOrUpdateSelective(new AccountVo.Builder().setName("hello")
                    .setBalance(300).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Hessian AccountService returns: " + accountId);
    }

    public static void main(String[] args) {
//        testGetAccountService();
        testSaveAccountService();
    }

}
