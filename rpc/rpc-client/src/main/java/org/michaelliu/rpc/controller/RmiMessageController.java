package org.michaelliu.rpc.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.michaelliu.rpc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Michael on 7/14/16
 */
@Controller
@RequestMapping("/rmi/message")
public class RmiMessageController {

    private static final Log log = LogFactory.getLog(RmiMessageController.class);

    @Autowired
    private MessageService rmiMessageService;

    /**
     * http://localhost:8081/rpc/rmi/message/send
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(@RequestBody JSONObject message) {
        log.info("sendMessage receives: " + message);
        String rspStr = rmiMessageService.echo(message.getString("message"));
        log.info("Message server echos: " + rspStr);
        return rspStr;
    }


}
