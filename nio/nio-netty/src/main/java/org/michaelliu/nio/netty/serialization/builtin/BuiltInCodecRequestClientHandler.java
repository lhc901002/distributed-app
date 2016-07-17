package org.michaelliu.nio.netty.serialization.builtin;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.michaelliu.nio.netty.serialization.RequestInfo;
import org.michaelliu.nio.netty.serialization.ResponseInfo;

import java.util.logging.Logger;

/**
 * Created by Michael on 7/6/16.
 */
public class BuiltInCodecRequestClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(BuiltInCodecRequestClientHandler.class.getName());

    private RequestInfo requestInfo;

    public BuiltInCodecRequestClientHandler(RequestInfo requestInfo) {
        this.requestInfo = requestInfo;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 10; i++) {
            requestInfo.setId(i);
            ctx.writeAndFlush(requestInfo);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ResponseInfo responseInfo = (ResponseInfo)msg;
        System.out.println("Receive server response : [" + responseInfo + "]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 释放资源
        logger.warning("Unexpected exception from downstream: " + cause.getMessage());
        ctx.close();
    }


}
