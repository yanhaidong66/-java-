package top.haidong556.oauth.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import top.haidong556.oauth.repository.UserRepository;
import top.haidong556.oauth.entity.MyUser;
import top.haidong556.oauth.mapper.UserMapper;

@SpringBootTest(
        classes = {
                UserServiceImpl.class,UserRepository.class, UserMapper.class
        }
)


class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;

    @Test

    void loadUserByUsername() {
        UserDetails userDetails = userService.loadUserByUsername("haidong");
        System.out.println(userDetails.toString());

    }

    @Test
    void updatePassword() {
        MyUser user=new MyUser("haidong","555");
        user.setEmail("123");
        UserDetails userDetails = userService.updatePassword(user, "333");
        System.out.println(userDetails);
    }

    @Test
    void getUserByUserName() {
        System.out.println(userService.getUserByUserName("haidong1"));
    }

    @Test
    void addUser() {
        MyUser user=new MyUser("haidong1","555");
        user.setEmail("123");
        userService.addUser(user);
    }


}