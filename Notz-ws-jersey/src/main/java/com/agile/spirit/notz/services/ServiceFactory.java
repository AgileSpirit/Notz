package com.agile.spirit.notz.services;

import com.agile.spirit.notz.services.NoteService;
import com.agile.spirit.notz.services.NoteServiceImpl;
import com.agile.spirit.notz.services.UserService;
import com.agile.spirit.notz.services.UserServiceImpl;

public class ServiceFactory {

  private static ServiceFactory instance;

  public static ServiceFactory getInstance() {
    if (instance == null) {
      instance = new ServiceFactory();
    }
    return instance;
  }

  private final UserService userService = new UserServiceImpl();
  
  private final NoteService noteService = new NoteServiceImpl();
  
  public UserService getUserService() {
    return userService;
  }

  public NoteService getNoteService() {
    return noteService;
  }

}
