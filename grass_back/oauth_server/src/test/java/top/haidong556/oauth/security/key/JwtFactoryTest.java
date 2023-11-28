package top.haidong556.oauth.security.key;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.security.PublicKey;

class JwtFactoryTest {

    @Test
    void getJwt() {
        JwtFactory.getJwt("df","fd");
    }

    @Test
    void getPublicKey() {
        String token=JwtFactory.getJwt("df","fd");
        PublicKey key=JwtFactory.getPublicKey();
        System.out.println(token.toLowerCase());
        System.out.println("------------");
        System.out.println(key.toString());
    }

    @Test
    void verify() {

        while(true) {
            String token=JwtFactory.getJwt("df","fd");
            PublicKey key=JwtFactory.getPublicKey();
            System.out.println(JwtFactory.getPublicKeyJson());

        }

    }

    @Test
    void getTokenInfo() {
        String token=JwtFactory.getJwt("df","fd");
        DecodedJWT decodedJWT = JwtFactory.getTokenInfo(token);
        System.out.println(decodedJWT.getIssuer());

    }
}