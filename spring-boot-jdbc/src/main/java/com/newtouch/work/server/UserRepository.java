package com.newtouch.work.server;

import com.newtouch.work.bean.User;

import java.util.List;

public interface UserRepository {
    int save(User user);
    int update(User user);
    int delete(long id);
    List<User> findALL();
    User findById(long id);
}
