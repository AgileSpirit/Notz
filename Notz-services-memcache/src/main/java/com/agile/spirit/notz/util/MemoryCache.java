package com.agile.spirit.notz.util;

import java.util.HashMap;
import java.util.Map;

import com.agile.spirit.notz.domain.User;

public class MemoryCache {

  private static MemoryCache instance;

  public static MemoryCache getInstance() {
    if (instance == null) {
      instance = new MemoryCache();
    }
    return instance;
  }

  private MemoryCache() {
  }

  private String currVal = getNextVal();

  private final Map<String, User> data = new HashMap<String, User>();

  public final Map<String, User> getData() {
    return this.data;
  }

  public String getNextVal() {
    this.currVal = DataGenerator.generateRandomString(12, true);
    return getCurrVal();
  }

  public String getCurrVal() {
    return this.currVal;
  }

}
