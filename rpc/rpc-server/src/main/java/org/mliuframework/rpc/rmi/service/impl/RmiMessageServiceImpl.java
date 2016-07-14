package org.mliuframework.rpc.rmi.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mliuframework.rpc.rmi.service.RmiMessageService;

/**
 * Created by Michael on 7/14/16.
 */
public class RmiMessageServiceImpl implements RmiMessageService {

    private static final Log log = LogFactory.getLog(RmiMessageServiceImpl.class);

    @Override
    public String echo(String message) {
        log.info("Message server receives: " + message);
        String rspStr = "";
        if (StringUtils.isEmpty(message)) {
            rspStr = "Message server has not received any message from client!";
        } else if (message.equals("quit") || message.equals("exit")) {
            rspStr = "Message server will be shutdown!";
        } else {
            rspStr = "Message server responses: " + message;
        }
        log.info("Message server responses: " + rspStr);
        return rspStr;
    }

}
