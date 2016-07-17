package org.michaelliu.nio.netty.decoder.fixedlength;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by Michael on 7/5/16.
 */
public class FixedLengthDecoderEchoServer {

    private final int port;
    private final int messageSize;

    public FixedLengthDecoderEchoServer(int port, int messageSize) {
        this.port = port;
        this.messageSize = messageSize;
    }

    public void run() throws Exception {
        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .option(ChannelOption.SO_BACKLOG, 100)
             .handler(new LoggingHandler(LogLevel.INFO))
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new FixedLengthFrameDecoder(messageSize));
                     ch.pipeline().addLast(new StringDecoder());
                     ch.pipeline().addLast(new FixedLengthDecoderEchoServerHandler());
                 }
             });

            // Start the server.
            ChannelFuture f = b.bind(port).sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8001;
        }
        String message = "Hello Michael, Welcome to Netty!";
        new FixedLengthDecoderEchoServer(port, message.length()).run();
    }

}
