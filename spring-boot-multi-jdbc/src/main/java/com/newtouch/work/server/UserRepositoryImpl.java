package com.newtouch.work.server;

import com.newtouch.work.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;
    @Override
    public int save(User user, JdbcTemplate jdbcTemplate) {
        String sql = "INSERT INTO users (name,password,age) values (?,?,?)";
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update(sql,user.getName(),user.getPassword(),user.getAge());
    }

    @Override
    public int update(User user, JdbcTemplate jdbcTemplate) {
        String sql =  "UPDATE users SET name = ? ,password = ?,age = ? WHERE id = ?";
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update(sql,user.getName(),user.getPassword(),user.getAge(),user.getId());
    }

    @Override
    public int delete(long id, JdbcTemplate jdbcTemplate) {
        String  sql  = "DELETE FROM  users WHERE id = ?" ;
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public List<User> findALL(JdbcTemplate jdbcTemplate) {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql,new UserRowMapper());
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User findById(long id, JdbcTemplate jdbcTemplate) {
        String sql  = "SELECT * FROM users WHERE id = ?";

        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<User>(User.class));

    }

    class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setAge(resultSet.getInt("age"));
            return user;
        }
    }
}
