package com.xialuo.shardingjdbcfkfb.dao;

import com.xialuo.shardingjdbcfkfb.entity.OtherTable;
import java.util.List;

public interface OtherTableDao {

  long addOne(OtherTable table);

  List<OtherTable> getAll();

}
