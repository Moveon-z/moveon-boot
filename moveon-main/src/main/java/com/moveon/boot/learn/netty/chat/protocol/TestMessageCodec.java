package com.moveon.boot.learn.netty.chat.protocol;

import com.moveon.boot.learn.netty.chat.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * @ClassName TestMessageCodec
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/24 16:08
 * @Version 1.0
 */
public class TestMessageCodec {

    public static void main(String[] args) throws Exception {
        EmbeddedChannel channel = new EmbeddedChannel(new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0), new LoggingHandler(), new MessageCodec());

        LoginRequestMessage message = new LoginRequestMessage("zhangsan", "123");
        channel.writeOutbound(message);

        // decode
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buf);

        channel.writeInbound(buf);
    }
}
