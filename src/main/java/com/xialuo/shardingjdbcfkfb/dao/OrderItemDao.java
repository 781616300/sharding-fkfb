package com.xialuo.shardingjdbcfkfb.dao;

import com.xialuo.shardingjdbcfkfb.entity.OrderItem;
import java.util.List;

public interface OrderItemDao {

  void addOne(OrderItem orderItem);

  List<OrderItem> getByOrderId(int id);

  List<OrderItem> getOrderItemByPrice(int price);

  List<OrderItem> getOrderItemByOrderId(int id);


}
