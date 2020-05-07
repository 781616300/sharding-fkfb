package com.xialuo.shardingjdbcfkfb.jdbc;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class ShardJDBCTest {

  public static void main(String[] args) throws SQLException {
    // 配置真实数据源
    Map<String, DataSource> dataSourceMap = new HashMap<>();

    // 配置第一个数据源
    BasicDataSource dataSource1 = new BasicDataSource();
    dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource1.setUrl("jdbc:mysql://59.110.213.162:3339/shard0");
    dataSource1.setUsername("root");
    dataSource1.setPassword("123456");
    dataSourceMap.put("ds0", dataSource1);

    // 配置第二个数据源
    BasicDataSource dataSource2 = new BasicDataSource();
    dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource2.setUrl("jdbc:mysql://59.110.213.162:3339/shard1");
    dataSource2.setUsername("root");
    dataSource2.setPassword("123456");
    dataSourceMap.put("ds1", dataSource2);

    // 配置Order表规则
    TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
    orderTableRuleConfig.setLogicTable("order");
    orderTableRuleConfig.setActualDataNodes("ds${0..1}.order${0..1}");

    // 配置分库 + 分表策略
    orderTableRuleConfig.setDatabaseShardingStrategyConfig(
        new InlineShardingStrategyConfiguration("order_id", "ds${order_id % 2}"));
    orderTableRuleConfig.setTableShardingStrategyConfig(
        new InlineShardingStrategyConfiguration("order_id", "order${order_id % 2}"));

    // 配置分片规则
    ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
    shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

    Map<String, Object> map = new HashMap<>();

    Properties properties = new Properties();
    properties.setProperty("sql.show", "true");

    // 获取数据源对象
    DataSource dataSource = ShardingDataSourceFactory
        .createDataSource(dataSourceMap, shardingRuleConfig, map, properties);

    String sql = "SELECT * from order WHERE order_id=?";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, 2);
      System.out.println();
      try (ResultSet rs = preparedStatement.executeQuery()) {
        while (rs.next()) {
          // %2结果，路由到 shard1.order1
          System.out.println("order_id---------" + rs.getInt(1));
          System.out.println("user_id---------" + rs.getInt(2));
          System.out.println("create_time---------" + rs.getTime(3));
          System.out.println("total_price---------" + rs.getInt(4));
        }
      }
    }
  }
}
