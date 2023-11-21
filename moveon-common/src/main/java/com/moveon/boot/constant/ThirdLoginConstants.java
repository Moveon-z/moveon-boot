package com.moveon.boot.constant;

/**
 * @ClassName ThirdLoginConstants
 * @Description TODO
 * @Author Moveon
 * @Date 2023/11/15 09:24
 * @Version 1.0
 **/
public class ThirdLoginConstants {

    public static final String GITEE_CLIENT_ID = "6ae933f941fabfb8cbc0496a14ee1807edbe7c24f444674be4d70ad9be767089";

    public static final String GITEE_CLIENT_SECRET = "a23831ce6fc7ea267ce032ec228f5a15b815a1d8e1a1700a8fa21dd1b0464ff4";

    public static final String GITEE_CALLBACK = "http://localhost:8080/gitee";

    public static final String GITEE_TOKEN_URL = "https://gitee.com/oauth/token?grant_type=authorization_code" +
            "&client_id=" + GITEE_CLIENT_ID + "&client_secret=" + GITEE_CLIENT_SECRET + "&redirect_uri=" + GITEE_CALLBACK +
            "&code=";
}
