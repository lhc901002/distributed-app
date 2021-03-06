package org.michaelliu.nio.netty.decoder.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Michael on 7/5/16.
 */
@Sharable
public class DelimiterDecoderEchoServerHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(
            DelimiterDecoderEchoServerHandler.class.getName());

    private String delimiterStr;
    private int counter = 0;

    public DelimiterDecoderEchoServerHandler(String delimiterStr) {
        this.delimiterStr = delimiterStr;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        System.out.println("The server receive order: " + body + "  The counter is: " + ++counter);
        body += delimiterStr;
        ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        logger.log(Level.WARNING, "Unexpected exception from downstream.", cause);
        ctx.close();
    }

}
