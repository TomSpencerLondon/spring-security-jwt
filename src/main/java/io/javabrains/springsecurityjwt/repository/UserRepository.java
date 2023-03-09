package io.javabrains.springsecurityjwt.repository;

import io.javabrains.springsecurityjwt.models.MyUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private MyUser user;
    public MyUser save(MyUser user) {
        this.user = user;
        return user;
    }


    public MyUser findByUserName(String userName) {
        return this.user;
    }

}
