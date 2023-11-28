package top.haidong556.oauth.service;

import top.haidong556.oauth.entity.MyUser;

public interface UserService {
    public MyUser getUserByUserName(String userName);
    public int addUser(MyUser user);
}
