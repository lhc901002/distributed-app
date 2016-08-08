package org.michaelliu.nio.netty.message.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;

/**
 * Created by michael on 2016/8/7.
 */
public class MessageServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    public MessageServerInitializer(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(socketChannel.alloc()));
        }
        //p.addLast(new LoggingHandler(LogLevel.INFO));
        p.addLast(new MessageServerHandler());
    }

}
