package com.moveon.boot.learn.netty.person.codec;

import com.google.gson.Gson;
import com.moveon.boot.learn.netty.person.entity.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @ClassName JsonObjectCodec
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/19 16:09
 * @Version 1.0
 */
public class JsonObjectCodec extends MessageToMessageCodec<ByteBuf, Response> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Response msg, List<Object> out) throws Exception {
        ByteBuf buf = ctx.alloc().buffer();
        // 1. 6个字节的魔数
        buf.writeBytes(new byte[]{'m', 'o', 'v', 'e', 'o', 'n'});
        buf.writeByte(msg.getSequenceId());
        buf.writeByte(msg.getMessageType());
        Gson gson = new Gson();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

    }
}
