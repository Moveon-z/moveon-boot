package com.moveon.boot.learn.netty;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NettyTest
 * @Description TODO
 * @Author Moveon
 * @Date 2024/5/24 11:08
 * @Version 1.0
 **/
public class NettyTest {

    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream(NettyTest.class.getClassLoader().getResource("data.txt").getPath()).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int len = channel.read(buffer);
                if (len == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
