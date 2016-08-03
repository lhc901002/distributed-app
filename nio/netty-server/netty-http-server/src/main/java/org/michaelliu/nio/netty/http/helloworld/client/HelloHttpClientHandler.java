package org.michaelliu.nio.netty.http.helloworld.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Michael on 8/3/16.
 */
public class HelloHttpClientHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static Log log = LogFactory.getLog(HelloHttpClientHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) {
        // processing http headers
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            log.info("Content type: " + response.headers().get(HttpHeaderNames.CONTENT_TYPE));
        }

        // processing http content
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            log.info(content.content().toString(CharsetUtil.UTF_8));

            if (content instanceof LastHttpContent) {
                System.err.println("} END OF CONTENT");
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
