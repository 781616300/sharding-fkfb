package com.xialuo.shardingjdbcfkfb.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Order {

  private long orderId;
  private long userId;
  private Date createTime;
  private long totalPrice;

}
