package top.haidong556.oauth.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import top.haidong556.oauth.common.MySqlSessionFactory;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.mapper.UserMapper;

@Repository
public class UserRepository {

    private SqlSessionFactory sqlSessionFactory;

    public UserRepository(){
        sqlSessionFactory= MySqlSessionFactory.getFactory();
    }
    public MyUser getUserByUserName(String userName){
        SqlSession sqlSession= sqlSessionFactory.openSession(false);
        MyUser user= sqlSession.getMapper(UserMapper.class).getUserByUserName(userName);
        sqlSession.commit();
        sqlSession.close();
        return user;
    };
    public int addUser(MyUser user){
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        int result=sqlSession.getMapper(UserMapper.class).addUser(user);
        sqlSession.commit();
        sqlSession.close();
        return result;
    };

    public int deleteUser(String userName){
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        int result=sqlSession.getMapper(UserMapper.class).deleteUser(userName);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    public int updatePasswordByUserName(String userName,String password){
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        int result=sqlSession.getMapper(UserMapper.class).updatePasswordByUserName(userName,password);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }
    public MyUser getUserById(int id){
        SqlSession sqlSession=sqlSessionFactory.openSession(false);
        MyUser user=sqlSession.getMapper(UserMapper.class).getUserById(id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }
}
