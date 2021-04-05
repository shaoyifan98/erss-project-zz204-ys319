package edu.duke.erss.dao;

import edu.duke.erss.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> getAllUser();
}
