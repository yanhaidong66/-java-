package top.haidong556.oauth.security.key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Date;

/**
 * RSA密钥对
 * **/
@PropertySource("classpath:key-config.properties")
public class MyKeyPair {

    @Value("${server-key-expired-millis}")
    private static long EXPIRE_TIME_MILLIS=12000;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private volatile Date keyCreateTime;
    private  volatile Date keyExpiredTime;

    public MyKeyPair(){
        // 使用安全随机数生成器
        SecureRandom secureRandom = new SecureRandom();

        // 创建RSA密钥对生成器
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // 设置密钥大小（2048位,256字节）
        int keySize = 2048;
        try {
            keyPairGenerator.initialize(new RSAKeyGenParameterSpec(keySize, RSAKeyGenParameterSpec.F4), secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }

        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Date current=new Date();


        // 获取公钥和私钥
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        keyCreateTime=current;
        keyExpiredTime=new Date(current.getTime()+EXPIRE_TIME_MILLIS);
    }
    public PrivateKey getPrivateKey(){
        return privateKey;
    }
    public PublicKey getPublicKey(){
        return publicKey;
    }
    public Date getKeyCreateTime(){
        return keyCreateTime;
    }
    public Date getKeyExpiredTime(){
        return keyExpiredTime;
    }
    public  String getPublicKeyJson(){
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        BigInteger publicExponent = rsaPublicKey.getPublicExponent();
        BigInteger modulus = rsaPublicKey.getModulus();
        return "{"
                + "\"algorithm\""+":"+"\""+publicKey.getAlgorithm()+"\""+","
                + "\"format\""+":"+"\""+publicKey.getFormat()+"\""+","
                + "\"exponent\""+":"+"\""+publicExponent.toString()+"\""+","
                + "\"modulus\""+":"+"\""+modulus.toString()+"\""+","
                + "\"expired\""+":"+"\""+keyExpiredTime.toString()+"\""+","
                + "\"createTime\""+":"+"\""+keyCreateTime.toString()+"\""+","
                + "\"issuer\""+":"+"\"grassOauth\""
                + "}";
    }

}
