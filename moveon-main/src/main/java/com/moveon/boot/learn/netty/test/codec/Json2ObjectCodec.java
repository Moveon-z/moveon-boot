package com.moveon.boot.learn.netty.test.codec;

import com.google.gson.Gson;
import com.moveon.boot.learn.netty.test.common.Message;
import com.moveon.boot.learn.netty.test.entity.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Json2ObjectCodec extends MessageToMessageCodec<ByteBuf, Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message msg, List list) throws Exception {
        ByteBuf buf = channelHandlerContext.alloc().buffer();
        // 魔数
        buf.writeBytes(new byte[]{'m', 'o', 'v', 'e', 'o', 'n'});
        // 版本号
        buf.writeByte(1);
        // 响应序列号
        buf.writeByte(msg.getSequenceId());
        // 响应类型
        buf.writeByte(msg.getMessageType());
        // 序列化方式
        buf.writeByte(msg.getSerializeType());
        Gson gson = new Gson();
        String json = gson.toJson(msg);
        // 响应长度
        buf.writeInt(json.length());
        // 响应数据
        buf.writeBytes(json.getBytes(StandardCharsets.UTF_8));
        list.add(buf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        // 魔数
        byteBuf.readBytes(6);
        // 版本号
        byteBuf.readByte();
        // 响应序列号
        byteBuf.readByte();
        // 响应类型
        byte messageType = byteBuf.readByte();
        // 序列化方式
        byteBuf.readByte();
        // 响应长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes, 0, length);
        // 读取到bytes里的内容为json串
        Gson gson = new Gson();
        Message res = gson.fromJson(new String(bytes, StandardCharsets.UTF_8), Message.class);
        list.add(res);
    }

}
