package org.michaelliu.rpc.service.rmi;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.dao.AccountMapper;
import org.michaelliu.entity.Account;
import org.michaelliu.rpc.service.AccountService;

import java.util.List;

/**
 * Created by Michael on 8/4/16.
 *
 * RMI: rmi://localhost:1199/rmi/AccountService
 */
public class RmiAccountService implements AccountService {

    private static final Log log = LogFactory.getLog(RmiAccountService.class);

    private AccountMapper accountMapper;

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account findById(Long id) {
        log.info("findById receives id: " + id);
        return new Account(1l, "Michael");
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = Lists.newArrayList();
        accountList.add(new Account(1l, "Michael"));
        accountList.add(new Account(2l, "Steven"));
        accountList.add(new Account(3l, "Jackson"));
        log.info("Account server responses: " + JSON.toJSONString(accountList));
        return accountList;
    }

}
