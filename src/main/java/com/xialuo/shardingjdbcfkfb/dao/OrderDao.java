package com.xialuo.shardingjdbcfkfb.dao;

import com.xialuo.shardingjdbcfkfb.entity.Order;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDao {

  long addOne(Order order);

  Order selectOneByOrderId(@Param("orderId") long orderId);

  Order selectOneByUserId(@Param("userId") int userId);

  List<Order> getOrderByUserId(@Param("userId") int userId);

}
