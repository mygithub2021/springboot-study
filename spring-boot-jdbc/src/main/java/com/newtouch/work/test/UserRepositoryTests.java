package com.newtouch.work.test;

import com.newtouch.work.bean.User;
import com.newtouch.work.server.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public  void  testSave(){
        User user = new User("zhangsan","ppppp",12);
        userRepository.save(user);
    }

    @Test
    public  void  testUpdate(){
        User user  = new User("zhangsan1","22222",19);
        user.setId(2L);
        userRepository.update(user);
    }
    @Test
    public void testDelete(){
        userRepository.delete(1L);
    }
    @Test
    public  void testQuery(){
        List<User> users =userRepository.findALL();

        for(User u: users){
            System.out.println(u.getName()+"***"+u.getAge());
        }
    }



}
