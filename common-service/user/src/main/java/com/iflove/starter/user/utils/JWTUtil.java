package com.iflove.starter.user.utils;

import com.alibaba.fastjson2.JSON;
import com.iflove.starter.common.utils.JsonUtil;
import com.iflove.starter.user.core.UserInfoDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.iflove.starter.bases.constant.UserConstant.*;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote JWT工具类
 */

@Slf4j
public class JWTUtil {
    // 过期时间 24小时，秒为单位
    private static final long EXPIRATION = 86400L;
    // token前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    // token发行者
    public static final String ISS = "Itinerary-Planning-System";
    public static final String SECRET = "r-k.Uv4D@rrX2aYiLOJJC-)!XBD=-#[^i,&vykXQYtU6p.pF4'xQ#GZ-4AS+ri)vhBEAyQFfpZ+";
    public static final SecretKey KEY = new SecretKeySpec(
            Arrays.copyOf(SECRET.getBytes(StandardCharsets.UTF_8), 64), "AES");

    /**
     * 生成用户 Token
     *
     * @param userInfo 用户信息
     * @return 用户访问 Token
     */
    public static String generateAccessToken(UserInfoDTO userInfo) {
        Map<String, Object> customerUserMap = new HashMap<>();
        customerUserMap.put(USER_ID_KEY, userInfo.getUserId());
        customerUserMap.put(USER_NAME_KEY, userInfo.getUsername());
        customerUserMap.put(USER_TOKEN_KEY, userInfo.getTokenId());
        String jwtToken = Jwts.builder()
                .claims(customerUserMap)
                .issuedAt(new Date())
                .issuer(ISS)
                .subject(JsonUtil.toStr(customerUserMap))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
                .encryptWith(KEY, Jwts.ENC.A256CBC_HS512)
                .compact();
        return TOKEN_PREFIX + jwtToken;
    }

    /**
     * 解析用户 Token
     *
     * @param jwtToken 用户访问 Token
     * @return 用户信息
     */
    public static UserInfoDTO parseJwtToken(String jwtToken) {
        if (StringUtils.hasText(jwtToken)) {
            String actualJwtToken = jwtToken.replace(TOKEN_PREFIX, "");
            try {
                Claims claims = Jwts.parser()
                        .verifyWith(KEY)
                        .decryptWith(KEY)
                        .build()
                        .parseEncryptedClaims(actualJwtToken)
                        .getPayload();
                Date expiration = claims.getExpiration();
                if (expiration.after(new Date())) {
                    String subject = claims.getSubject();
                    return JsonUtil.toObj(subject, UserInfoDTO.class);
                }
            } catch (ExpiredJwtException ignored) {
            } catch (Exception ex) {
                log.error("JWT Token解析失败，请检查", ex);
            }
        }
        return null;
    }

}
