package org.michaelliu.nio.netty.message.server;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by michael on 2016/8/7.
 */
@Sharable
public class MessageServerHandler extends ChannelInboundHandlerAdapter {

    private static Log log = LogFactory.getLog(MessageServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("Message Server receives: " + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
