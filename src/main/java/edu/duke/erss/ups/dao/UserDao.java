package edu.duke.erss.ups.dao;

import edu.duke.erss.ups.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> getAllUser();

    List<User> getUserByName(@Param("username") String username);
}
