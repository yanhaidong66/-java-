package top.haidong556.oauth.security.key;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;

/**
 * 单例模式
 * 可以获得jwtToken
 * 可以获得当前jwt的RSA公钥
 * 可以验证jwtToken的合法性
 * 可以获得jwtToken的信息
 */
@PropertySource("classpath:key-config.properties")
public class JwtFactory {
    private static final String ALGORITHM="RSA256";
    private static final String TYPE="jwt";
    private static final String ISSUER="grass_oauth";
    @Value("${client-key-expired-millis}")
    private static long EXPIRED=100000;
    private static final String AUDIENCE="-1";
    private static final String ISSUE_AT="-1";
    private static final String JWT_ID="-1";
    private static final String USER_ID="-1";
    private static final String USER_NAME="-1";
    private static final String AUTHORITY="-1";
    private static final  JwtKeyProvider keyProvider= JwtKeyProvider.getInstance();


    public static String getJwt(String audience,String authority){
        HashMap<String, Object> head=new HashMap<>();
        head.put("type",TYPE);
        head.put("algorithm",ALGORITHM);

        HashMap<String,String> payload=new HashMap<>();
        payload.put("authority",authority);


        Date issuedAt=new Date();
        Date expiredAt=new Date(issuedAt.getTime()+EXPIRED);



        JWTCreator.Builder jwtBuilder=JWT.create();
        String jwtToken=jwtBuilder.withExpiresAt(expiredAt)
                .withAudience(audience)
                .withIssuedAt(issuedAt)
                .withJWTId(JWT_ID)
                .withIssuer(ISSUER)
                .withHeader(head)
                .withPayload(payload)
                .sign(Algorithm.RSA256(JwtKeyProvider.getInstance()));

        return jwtToken;

    }
    public static PublicKey getPublicKey(){
        return keyProvider.getPublicKeyById("");
    }

    public static String getPublicKeyJson(){return keyProvider.getPublicKeyJson();}

    /**
     * 验证token  合法性
     */
    public static DecodedJWT verify(String token) {

        JWTVerifier jwt = JWT.require(Algorithm.RSA256(keyProvider.getPublicKeyById(""), null))
                .build();

        return jwt.verify(token);


    }

    /**
     * 获取token信息方法
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.RSA256(keyProvider.getPublicKeyById(""),null)).build().verify(token);
        return verify;
    }

}
