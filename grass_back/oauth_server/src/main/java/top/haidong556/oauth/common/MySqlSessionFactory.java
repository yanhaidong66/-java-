package top.haidong556.oauth.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class MySqlSessionFactory {
    private static final SqlSessionFactory sqlFactory=getSqlFactory();

    private MySqlSessionFactory(){

    }
    private static SqlSessionFactory getSqlFactory() {
        String resource="mybatis-conf.xml";
        InputStream config;
        try {
            config= Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new SqlSessionFactoryBuilder().build(config);
    }
    public static SqlSessionFactory getFactory(){
        return sqlFactory;
    }
}
