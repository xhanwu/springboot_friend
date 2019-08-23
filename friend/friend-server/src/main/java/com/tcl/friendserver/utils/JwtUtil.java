package com.tcl.friendserver.utils;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final Logger Log = LoggerFactory.getLogger(JwtUtil.class);
    //加密秘钥
    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";
    private static final String EXP = "exp";
    private static final String PAYLOAD = "payload";

    /**
     * JWT签名，返回token
     *
     * @param payload single string or JSON string
     * @return JWT token
     */
    public static String sign(String payload) {
        Log.info("sign : ++");
        Log.info("sign : payload = " + payload);
        try {
            JWTSigner jwtSigner = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<>();
            claims.put(PAYLOAD, payload);
            //24h
            int EXPIRE_TIME = 24 * 60 * 60 * 1000;
            claims.put(EXP, System.currentTimeMillis() + EXPIRE_TIME);
            return jwtSigner.sign(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 如果jwt没有超时，返回jwt token中包含的json数据
     *
     * @param token
     * @return
     */
    public static String unSign(String token) {
        Log.info("unSign : ++");
        try {
            JWTVerifier jwtVerifier = new JWTVerifier(SECRET);
            Map<String, Object> claims = jwtVerifier.verify(token);
            long exp = (Long) claims.get(EXP);
            long currentTimeMillis = System.currentTimeMillis();
            if (exp > currentTimeMillis) {
                return (String) claims.get(PAYLOAD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
