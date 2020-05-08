package com.xialuo.shardingjdbcfkfb.dao;

import com.xialuo.shardingjdbcfkfb.entity.User;
import java.util.List;

public interface UserDao {

  void addOne(User user);

  User getOneById(long id);
  List<User> getByAge(long age);
}
