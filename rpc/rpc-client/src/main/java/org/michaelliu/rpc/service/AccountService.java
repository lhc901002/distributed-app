package org.michaelliu.rpc.service;

import org.michaelliu.rpc.entity.Account;

import java.util.List;

/**
 * Created by Michael on 7/14/16
 *
 * RMI: rmi://localhost:1199/RmiAccountService
 * Hessian: http://localhost:8080/remoting/HessianAccountService
 */
public interface AccountService {

    void saveOrUpdate(Account account);

    List<Account> findAll();

}
