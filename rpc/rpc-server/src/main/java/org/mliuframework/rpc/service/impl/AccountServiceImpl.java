package org.mliuframework.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mliuframework.rpc.entity.Account;
import org.mliuframework.rpc.service.AccountService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michael on 7/14/16.
 */
public class AccountServiceImpl implements AccountService {

    private static final Log log = LogFactory.getLog(AccountServiceImpl.class);

    @Override
    public void saveOrUpdate(Account account) {
        log.info("Account server receives: " + JSON.toJSONString(account));
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new LinkedList<>();
        accountList.add(new Account(1l, "Michael"));
        accountList.add(new Account(2l, "Steven"));
        accountList.add(new Account(3l, "Jackson"));
        log.info("Account server responses: " + JSON.toJSONString(accountList));
        return accountList;
    }

}
