package org.michaelliu.rpc.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.rpc.service.MessageService;

/**
 * Created by Michael on 7/14/16.
 */
public class MessageServiceImpl implements MessageService {

    private static final Log log = LogFactory.getLog(MessageServiceImpl.class);

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
