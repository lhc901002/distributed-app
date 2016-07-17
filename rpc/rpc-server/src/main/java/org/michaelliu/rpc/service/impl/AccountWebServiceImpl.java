package org.michaelliu.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.rpc.entity.WsAccount;
import org.michaelliu.rpc.service.AccountWebService;

import javax.jws.WebService;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 7/14/16.
 */
@WebService(endpointInterface = "org.michaelliu.rpc.service.AccountWebService")
public class AccountWebServiceImpl implements AccountWebService {

    private static final Log log = LogFactory.getLog(AccountWebServiceImpl.class);

    @Override
    public void saveOrUpdate(WsAccount account) {
        log.info("Account server receives: " + JSON.toJSONString(account));
    }

    @Override
    public List<WsAccount> findAll() {
        List<WsAccount> accountList = new LinkedList<>();
        accountList.add(new WsAccount(1l, "Michael"));
        accountList.add(new WsAccount(2l, "Steven"));
        accountList.add(new WsAccount(3l, "Jackson"));
        log.info("Account server responses: " + JSON.toJSONString(accountList));
        return accountList;
    }

}
