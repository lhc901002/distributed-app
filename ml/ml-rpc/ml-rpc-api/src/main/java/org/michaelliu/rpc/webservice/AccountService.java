package org.michaelliu.rpc.webservice;

import org.michaelliu.entity.Account;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by michael on 2016/8/5.
 */
@WebService
public interface AccountService {

    Account findById(Long id);

    List<Account> findAll();

}
