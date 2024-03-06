package com.moveon.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ThreadProperties
 * @Description TODO
 * @Author Moveon
 * @Date 2024/2/8 20:13
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "moveon.thread")
@Component
@Data
public class ThreadProperties {

    private Integer coreSize = 10;

    private Integer maxSize = 200;

    private Integer keepAliveSeconds = 30;

    private Integer queueCapacity = 1000;

}
