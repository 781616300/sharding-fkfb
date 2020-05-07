package com.xialuo.shardingjdbcfkfb.service;

import com.xialuo.shardingjdbcfkfb.dao.UserDao;
import com.xialuo.shardingjdbcfkfb.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class User1Service {

  Logger logger = LoggerFactory.getLogger(User1Service.class);

  @Autowired
  private UserDao userDao;

  @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
  //@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
  public void test1Transactional() {
    User user2 = new User(12674, "青山", 12);
    this.userDao.addOne(user2);
  }
}
