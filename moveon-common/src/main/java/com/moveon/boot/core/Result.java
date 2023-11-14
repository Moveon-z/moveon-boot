package com.moveon.boot.core;

import java.util.HashMap;

/**
 * @ClassName Result
 * @Description TODO
 * @Author Moveon
 * @Date 2023/10/22 10:05
 * @Version 1.0
 **/
public class Result extends HashMap<String, Object> {

    public static final String CODE_TAG = "coed";

    public static final String MSG_TAG = "msg";

    public static final String DATA_TAG = "data";

    public Result() {
    }

    public Result(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public Result(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    public static Result success(String msg, Object data) {
        return new Result(200, msg, data);
    }

    public static Result success(String msg) {
        return new Result(200, msg);
    }

    public static Result error(String msg, Object data) {
        return new Result(500, msg, data);
    }

    public static Result error(String msg) {
        return new Result(500, msg);
    }

}
