package com.agile.spirit.notz.services.util;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

  private static final long serialVersionUID = 1L;

  private String pattern = null;

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  public boolean isEmpty() {
    return pattern == null;
  }

}
