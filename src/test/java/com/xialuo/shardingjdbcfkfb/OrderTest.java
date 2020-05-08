package com.xialuo.shardingjdbcfkfb;

import com.xialuo.shardingjdbcfkfb.dao.OrderDao;
import com.xialuo.shardingjdbcfkfb.dao.OrderItemDao;
import com.xialuo.shardingjdbcfkfb.entity.Order;
import com.xialuo.shardingjdbcfkfb.entity.OrderItem;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    /**
     * 分库分表插入数据测试
     * 对userId取模
     */
    @Test
    public void insertData() throws Exception {
        // 插入订单
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setOrderId(i);
            order.setUserId(2673);
            order.setTotalPrice(666);
            order.setCreateTime(new Date());
            this.orderDao.addOne(order);
            log.info("插入订单id:{}", order.getOrderId());

            //// 插入订单明细
            //OrderItem item = new OrderItem();
            //item.setOrderId(order.getOrderId());
            //item.setName("huawei");
            //item.setPrice(333);
            //item.setUserId(2673);
            //this.orderItemDao.addOne(item);
            //log.info("插入订单明细id：{}", item.getOrderItemId());
        }
    }

    @Test
    public void insertData1() throws Exception {
        for (int i = 0; i < 100; i++) {
            // 插入订单明细
            OrderItem item = new OrderItem();
            item.setOrderId(i);
            item.setName("huawei");
            item.setPrice(333);
            item.setUserId(2673);
            this.orderItemDao.addOne(item);
            //log.info("插入订单明细id：{}", item.getOrderItemId());
        }
    }

    /**
     * 查询用户订单
     */
    @Test
    public void selectOneByUserId() {
        Order order = this.orderDao.selectOneByUserId(2673);
        log.info("查询用户订单结果为：{}", order);
    }
    @Test
    public void selectOneByOrderId() {
        Order order = this.orderDao.selectOneByOrderId(1);
        log.info("查询用户订单结果为：{}", order);
    }

    @Test
    public void getOrderByUserId() {
        List<Order> orderByUserId = this.orderDao.getOrderByUserId(2673);
        log.info("查询用户订单结果为：{}", orderByUserId);
    }

    /**
     * 关联查询
     * 没有使用分片键，会产生笛卡尔积查询
     */
    @Test
    public void selectOrderItem() {
        List<OrderItem> orderItems = this.orderItemDao.getOrderItemByPrice(333);
        log.info("查询到结果为：{}", Arrays.toString(orderItems.toArray()));
    }

    /**
     * 关联查询
     * 使用分片键，不会产生笛卡尔积查询
     */
    @Test
    public void selectOrder() {
        List<OrderItem> orderItems = this.orderItemDao.getOrderItemByOrderId(1);
        log.info("查询到结果为：{}", Arrays.toString(orderItems.toArray()));
    }

}

