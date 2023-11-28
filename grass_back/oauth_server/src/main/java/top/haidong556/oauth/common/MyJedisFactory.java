package top.haidong556.oauth.common;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

@Component
public class MyJedisFactory {
    private static final JedisPool jedisPool=createJedisPool();
    private static final String jedisConfigPath="jedis-config.properties";

    private static JedisPool createJedisPool() {
        InputStream inputStream=ClassLoader.getSystemResourceAsStream(jedisConfigPath);
        Properties properties=new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxWait(Duration.ofMillis(Long.parseLong(properties.getProperty("jedis.pool.maxWaitMills"))));
        config.setMaxTotal(Integer.parseInt(properties.getProperty("jedis.pool.maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("jedis.pool.maxIdle")));
        config.setMinIdle(Integer.parseInt(properties.getProperty("jedis.pool.minIdle")));
        return new JedisPool(config,properties.getProperty("jedis.ip"),Integer.parseInt(properties.getProperty("jedis.port")));
    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
