package org.michaelliu.nio.netty.serialization.builtin;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.michaelliu.nio.netty.serialization.RequestInfo;
import org.michaelliu.nio.netty.serialization.ResponseInfo;

/**
 * Created by Michael on 7/6/16.
 */
@ChannelHandler.Sharable
public class BuiltInCodecRequestServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestInfo requestInfo = (RequestInfo)msg;
        if (1 == requestInfo.getType()) {
            System.out.println("Service accept client request : ["
                    + requestInfo + "]");
            ctx.writeAndFlush(new ResponseInfo(0, "success"));
        } else {
            System.out.println("Service deny client request : ["
                    + requestInfo + "]");
            ctx.writeAndFlush(new ResponseInfo(-1, "fail"));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}
