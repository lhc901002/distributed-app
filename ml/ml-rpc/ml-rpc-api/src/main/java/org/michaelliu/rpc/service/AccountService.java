package org.michaelliu.rpc.service;

import org.michaelliu.entity.Account;

import java.util.List;

/**
 * Created by Michael on 8/4/16.
 */
public interface AccountService {

    Account findById(Long id);

    List<Account> findAll();

}
