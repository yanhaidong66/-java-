package top.haidong556.oauth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
@Data
public class MyUser implements UserDetails {
    private int id;
    private String userName;
    private String password;
    private String email;
    private Date createTime;
    private Date modifiedTime;
    public MyUser(){

    };

    public MyUser(int id, String userName, String password, String email, Date createTime, Date modifiedTime) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    public MyUser(String userName, String password){
        this.userName=userName;
        this.password=password;
    };

    public MyUser(int id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public MyUser(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
