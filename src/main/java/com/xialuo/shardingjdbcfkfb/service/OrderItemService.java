package com.xialuo.shardingjdbcfkfb.service;

import com.xialuo.shardingjdbcfkfb.dao.OrderItemDao;
import com.xialuo.shardingjdbcfkfb.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

  @Autowired
  private OrderItemDao orderItemDao;

  public long addOne(OrderItem item) {
    this.orderItemDao.addOne(item);
    return item.getOrderItemId();
  }


}
