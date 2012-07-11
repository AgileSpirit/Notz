package com.agile.spirit.notz.ui;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.agile.spirit.notz.domain.User;

public class NotzSession extends WebSession {

  private static final long serialVersionUID = 1L;

  private User user = null;

  public NotzSession(Request request) {
    super(request);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public boolean isUserLoggedIn() {
    return user != null;
  }

  public boolean isUserNotLoggedIn() {
    return !isUserLoggedIn();
  }

}
