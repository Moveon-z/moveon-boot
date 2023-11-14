package com.moveon.boot.util.generator.id;

import com.moveon.boot.config.MoveonConfig;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

/**
 * @ClassName IDGenerator
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/29 08:30
 * @Version 1.0
 **/
public class IDGenerator {

    private static final long EPOCH = 1693065600000L; // 2023-08-27
    private static final long MACHINE_ID_BITS = 10;
    private static final long SEQUENCE_BITS = 12;
    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    private static long machineId = MoveonConfig.getMachineId();
    private static long sequence = 0;
    private static long lastTimestamp = -1;

    public static long generatorID() {
        long currentTimestamp = System.currentTimeMillis() - EPOCH;

        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate ID.");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // Sequence number overflow, wait until the next millisecond.
                while (currentTimestamp <= lastTimestamp) {
                    currentTimestamp = System.currentTimeMillis() - EPOCH;
                }
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = currentTimestamp;

        long id = (currentTimestamp << (MACHINE_ID_BITS + SEQUENCE_BITS)) |
                (machineId << SEQUENCE_BITS) |
                sequence;
        return id;
    }

}
