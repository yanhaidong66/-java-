package top.haidong556.oauth.service.impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import top.haidong556.oauth.common.MyJedisFactory;
import top.haidong556.oauth.repository.UserRepository;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.service.UserService;

import java.util.Date;

@Service
public class UserServiceImpl implements UserDetailsService, UserDetailsPasswordService, UserService {
    private final String REDIS_USER_USERNAME_KEY_PREFIX="oauth:user:user_username:";
    private final String REDIS_USER_USERID_KEY_PREFIX="oauth:user:user_id:";
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUserName(username);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        int userId=userRepository.updatePasswordByUserName(user.getUsername(),newPassword);
        return userRepository.getUserById(userId);
    }

    @Override
    public MyUser getUserByUserName(String userName) {
        Jedis jedisSession= MyJedisFactory.getJedis();
        String redisResult=jedisSession.get(REDIS_USER_USERNAME_KEY_PREFIX+userName);
        if(redisResult!=null)
            return JSON.parseObject(redisResult, MyUser.class);
        return userRepository.getUserByUserName(userName);
    }

    @Override
    public int addUser(MyUser user) {
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());
        int result=userRepository.addUser(user);
        Jedis jedisSession=MyJedisFactory.getJedis();
        jedisSession.set(REDIS_USER_USERNAME_KEY_PREFIX+user.getUsername(),JSON.toJSONString(user));
        jedisSession.set(REDIS_USER_USERID_KEY_PREFIX+user.getId(),JSON.toJSONString(user));
        return result;
    }
}
