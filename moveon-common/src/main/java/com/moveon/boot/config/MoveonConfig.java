package com.moveon.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName MoveonConfig
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/29 10:07
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "moveon")
public class MoveonConfig {

    private static int machineId;

    public static int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        MoveonConfig.machineId = machineId;
    }
}
