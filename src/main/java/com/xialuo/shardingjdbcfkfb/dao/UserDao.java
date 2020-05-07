package com.xialuo.shardingjdbcfkfb.dao;

import com.xialuo.shardingjdbcfkfb.entity.User;

public interface UserDao {

  void addOne(User user);

  User getOneById(long id);
}
