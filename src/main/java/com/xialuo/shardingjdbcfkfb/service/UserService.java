package com.xialuo.shardingjdbcfkfb.service;

import com.xialuo.shardingjdbcfkfb.dao.UserDao;
import com.xialuo.shardingjdbcfkfb.entity.User;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {
  @Autowired
  User1Service user1Service;

  Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserDao userDao;

  public long addOne(User user) {
    this.userDao.addOne(user);
    return user.getUserId();
  }

  public User getOne(long id) {
    return userDao.getOneById(id);
  }

  /**
   * 测试跨库事务
   */
  @ShardingTransactionType(TransactionType.XA)
  @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
  public void testTransactional() {
    User user1 = new User(12673, "青山", 12);
    this.userDao.addOne(user1);
    //另一个事务
    user1Service.test1Transactional();

    // 主键冲突
    User user2 = new User(12675, "青山", 12);
    this.userDao.addOne(user2);
  }

}
