package com.xialuo.shardingjdbcfkfb.service;

import com.xialuo.shardingjdbcfkfb.dao.OrderDao;
import com.xialuo.shardingjdbcfkfb.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderDao orderDao;

  public long insertOne(Order order) {
    this.orderDao.addOne(order);
    return order.getOrderId();
  }
}
