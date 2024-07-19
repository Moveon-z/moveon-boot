package com.moveon.boot;

import io.netty.channel.ChannelFuture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class MainApplication
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        context.getBean(ChannelFuture.class).channel().closeFuture().syncUninterruptibly();
    }
}
