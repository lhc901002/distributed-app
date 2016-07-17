package org.michaelliu.nio.netty.decoder.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by Michael on 7/5/16.
 */
public class DelimiterDecoderEchoClient {

    private final String host;
    private final int port;
    private final String message;
    private final String delimiterStr;

    public DelimiterDecoderEchoClient(String host, int port, String message, String delimiterStr) {
        this.host = host;
        this.port = port;
        this.message = message;
        this.delimiterStr = delimiterStr;
    }

    public void run() throws Exception {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ByteBuf delimiter = Unpooled.copiedBuffer(delimiterStr.getBytes());
                     ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                     ch.pipeline().addLast(new StringDecoder());
                     ch.pipeline().addLast(new DelimiterDecoderEchoClientHandler(message));
                 }
             });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new DelimiterDecoderEchoClient("127.0.0.1", 8001, "Hello, Michael! Welcome to Netty World!$_", "$_").run();
    }

}
