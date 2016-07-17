package org.michaelliu.rpc.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.rpc.entity.Account;
import org.michaelliu.rpc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Michael on 7/14/16
 */
@Controller
@RequestMapping("/rmi/account")
public class RmiAccountController {

    private static final Log log = LogFactory.getLog(RmiAccountController.class);

    @Autowired
    private AccountService rmiAccountService;

    /**
     * http://localhost:8081/rpc/rmi/account/save
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveAccount(@RequestBody Account account) {
        log.info("saveAccount receives: " + JSON.toJSONString(account));
        rmiAccountService.saveOrUpdate(account);
        return "ok";
    }

    /**
     * http://localhost:8081/rpc/rmi/account/list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> listAccounts() {
        List<Account> accountList = rmiAccountService.findAll();
        log.info("listAccounts returns: " + JSON.toJSONString(accountList));
        return accountList;
    }

}
