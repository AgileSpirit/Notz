package com.agile.spirit.notz.services.session;

import java.util.HashMap;
import java.util.Map;

import com.agile.spirit.notz.domain.User;

public class SessionManager {

  private static final Map<String, User> connectedUsers = new HashMap<String, User>();
  
  private static SessionManager instance = null;
  
  private SessionManager () {
    super();
  }
  
  public static SessionManager getInstance() {
    if (instance == null) {
      instance = new SessionManager();
    }
    return instance;
  }
  
  public String addUserInSession(User user) {
    String key = user.getId();
    connectedUsers.put(key, user);
    return key;
  }
  
  public void removeUserFromSession(User user) {
    String key = user.getId();
    connectedUsers.remove(key);
  }
}
