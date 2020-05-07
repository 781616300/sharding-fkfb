package com.xialuo.shardingjdbcfkfb.service;

import com.xialuo.shardingjdbcfkfb.dao.DictionaryDao;
import com.xialuo.shardingjdbcfkfb.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

  @Autowired
  private DictionaryDao dictionaryDao;

  public long addOne(Dictionary dictionary) {
    this.dictionaryDao.addOne(dictionary);
    return dictionary.getDictionaryId();
  }
}
