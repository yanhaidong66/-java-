package top.haidong556.oauth.security;

import org.junit.jupiter.api.Test;
import top.haidong556.oauth.security.key.JwtFactory;

class MyAuthenticationHandlerTest {

    @Test
    void onAuthenticationSuccess() {
        System.out.println(JwtFactory.getPublicKeyJson());

//        String base64PublicKey = Base64.getEncoder().encodeToString(JwtFactory.getPublicKey().getEncoded());
//        System.out.println(base64PublicKey);
//        JSONObject publicKeyJSON = new JSONObject();
//        publicKeyJSON.put("publicKey", base64PublicKey);
//        System.out.println(publicKeyJSON);
//
//        JSONObject jsonObject = JSONObject.parseObject(publicKeyJSON);
//
//        // 从JSON对象中获取Base64编码的公钥字符串
//        String base64PublicKey1 = jsonObject.getString("publicKey");
//
//        // 将Base64编码的字符串解码为字节数组
//        byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);
//
//        // 使用字节数组创建公钥对象
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
//        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
//
//        // 现在，publicKey 变量包含了还原的公钥对象
//        System.out.println("Restored Public Key: " + publicKey);
    }
}