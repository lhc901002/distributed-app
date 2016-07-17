package org.michaelliu.rpc.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.rpc.entity.WsAccount;
import org.michaelliu.rpc.service.AccountWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Michael on 7/14/16.
 */
@Controller
@RequestMapping("/webservice/account")
public class WsAccountController {

    private static final Log log = LogFactory.getLog(HessianMessageController.class);

    @Autowired
    private AccountWebService accountWebService;

    /**
     * http://localhost:8081/rpc/webservice/account/save
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveAccount(@RequestBody WsAccount account) {
        log.info("saveAccount receives: " + JSON.toJSONString(account));
        accountWebService.saveOrUpdate(account);
        return "ok";
    }

    /**
     * http://localhost:8081/rpc/webservice/account/list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<WsAccount> listAccounts() {
        List<WsAccount> accountList = accountWebService.findAll();
        log.info("listAccounts returns: " + JSON.toJSONString(accountList));
        return accountList;
    }

}
