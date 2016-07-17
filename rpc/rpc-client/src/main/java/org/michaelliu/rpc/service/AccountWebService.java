package org.michaelliu.rpc.service;

import org.michaelliu.rpc.entity.WsAccount;

import javax.jws.WebService;
import java.util.List;

/**
 * Created by Michael on 7/14/16
 *
 * WS: http://localhost:8080/webservice/AccountWebService?wsdl
 */
@WebService
public interface AccountWebService {

    void saveOrUpdate(WsAccount account);

    List<WsAccount> findAll();

}
