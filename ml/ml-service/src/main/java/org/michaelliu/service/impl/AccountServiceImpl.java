package org.michaelliu.service.impl;

import org.michaelliu.dao.AccountMapper;
import org.michaelliu.entity.Account;
import org.michaelliu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Michael on 8/4/16.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account findById(Long id) {
        return null;
    }

}
