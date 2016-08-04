package org.michaelliu.rpc.service.cxf;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.dao.AccountMapper;
import org.michaelliu.entity.Account;
import org.michaelliu.rpc.webservice.AccountService;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by michael on 2016/8/5.
 *
 * WS: http://localhost:8080/webservice/AccountService?wsdl
 */
@WebService(endpointInterface = "org.michaelliu.rpc.webservice.AccountService")
public class CxfAccountService implements AccountService {

    private static final Log log = LogFactory.getLog(CxfAccountService.class);

    private AccountMapper accountMapper;

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account findById(Long id) {
        log.info("findById receives id: " + id);
        Account account = new Account(1l, "Michael");
        log.info("findById responses: " + JSON.toJSONString(account));
        return account;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = Lists.newArrayList();
        accountList.add(new Account(1l, "Michael"));
        accountList.add(new Account(2l, "Steven"));
        accountList.add(new Account(3l, "Jackson"));
        log.info("findAll responses: " + JSON.toJSONString(accountList));
        return accountList;
    }

}
