package org.michaelliu.nio.netty.decoder.line;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Michael on 7/5/16.
 */
public class LineDecoderEchoClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(
            LineDecoderEchoClientHandler.class.getName());

    private int counter = 0;
    private byte[] req;

    /**
     * Creates a client-side handler.
     */
    public LineDecoderEchoClientHandler(String message) {
        req = (message + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 20; i++) {
            ByteBuf firstMessage = Unpooled.buffer(req.length);
            firstMessage.writeBytes(req);
            ctx.writeAndFlush(firstMessage);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String)msg;
        System.out.println("This is: " + body + "  The counter is: " + ++counter);
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
