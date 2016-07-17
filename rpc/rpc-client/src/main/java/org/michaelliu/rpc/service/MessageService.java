package org.michaelliu.rpc.service;

/**
 * Created by Michael on 7/14/16
 *
 * RMI: rmi://localhost:1199/RmiMessageService
 * Hessian: http://localhost:8080/remoting/HessianMessageService
 */
public interface MessageService {

    String echo(String message);

}
