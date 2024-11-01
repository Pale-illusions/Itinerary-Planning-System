package com.iflove.starter.user.utils;

import com.alibaba.fastjson2.JSON;
import com.iflove.starter.user.core.UserInfoDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.iflove.starter.bases.constant.UserConstant.USER_ID_KEY;
import static com.iflove.starter.bases.constant.UserConstant.USER_NAME_KEY;

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
    public static final String SECRET = "SecretKey0392456789012320394876234567830923492889014029678901401145141919810";

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
        String jwtToken = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .issuedAt(new Date())
                .issuer(ISS)
                .subject(JSON.toJSONString(customerUserMap))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION * 1000))
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
                        .setSigningKey(SECRET)
                        .build()
                        .parseClaimsJws(actualJwtToken)
                        .getBody();
                Date expiration = claims.getExpiration();
                if (expiration.after(new Date())) {
                    String subject = claims.getSubject();
                    return JSON.parseObject(subject, UserInfoDTO.class);
                }
            } catch (ExpiredJwtException ignored) {
            } catch (Exception ex) {
                log.error("JWT Token解析失败，请检查", ex);
            }
        }
        return null;
    }

}
