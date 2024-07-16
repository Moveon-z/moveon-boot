package com.moveon.boot.learn.netty.c4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.apache.ibatis.javassist.compiler.ast.Variable;

import static com.moveon.boot.learn.netty.c4.TestByteBuf.log;

/**
 * @ClassName TestSlice
 * @Description TODO
 * @Author huangzh
 * @Date 2024/6/20 16:27
 * @Version 1.0
 */
public class TestSlice {

    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{'a', 'b', 'a', 'b', 'a', 'b', 'a', 'b', 'a', 'b'});
        log(buf);

        // 在切片过程中，没有发生数据复制
        ByteBuf f1 = buf.slice(0, 5);
        ByteBuf f2 = buf.slice(5, 5);
        log(f1);
        log(f2);
        System.out.println("================");
        f1.setByte(0, 'b');
        log(f1);
        log(buf);
    }
}
