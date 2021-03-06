package ru.smax.examples.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        final ByteBuf in = (ByteBuf) msg;

        if (log.isDebugEnabled()) {
            log.debug("echo: {}", in.toString(CharsetUtil.US_ASCII).trim());
        }

        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getLocalizedMessage());
        ctx.close();
    }
}
