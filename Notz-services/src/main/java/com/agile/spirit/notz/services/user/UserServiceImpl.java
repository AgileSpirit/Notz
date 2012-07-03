package com.agile.spirit.notz.services.user;

import java.util.ArrayList;
import java.util.List;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.note.NoteService;
import com.agile.spirit.notz.services.note.NoteServiceImpl;
import com.agile.spirit.notz.services.util.DataGenerator;
import com.agile.spirit.notz.services.util.SearchCriteria;

public class UserServiceImpl implements UserService {

  private static UserService instance;
  private final List<User> list = new ArrayList<User>();
  private Integer index = 1;

  private UserServiceImpl() {
  }

  public static UserService getInstance() {
    if (instance == null) {
      instance = new UserServiceImpl();
      DataGenerator.generateData();
    }
    return instance;
  }

  @Override
  public List<User> find(SearchCriteria criteria) {
    if (criteria == null || criteria.isEmpty()) {
      return new ArrayList<User>(list);
    }
    List<User> result = new ArrayList<User>();
    for (User user : list) {
      boolean isUserMatching = matchName(criteria.getPattern(), user);
      if (isUserMatching) {
        result.add(user);
      }
    }
    return result;
  }

  private boolean matchName(String pattern, User user) {
    if (pattern != null) {
      if (user.getEmail().toUpperCase().contains(pattern.toUpperCase())
          || user.getFirstName().toUpperCase().contains(pattern.toUpperCase())
          || user.getLastName().toUpperCase().contains(pattern.toUpperCase())) {
        return true;
      }
      return false;
    }
    return true;
  }

  @Override
  public User getById(Integer id) {
    if (id != null) {
      for (User user : list) {
        if (user.getId().equals(id)) {
          return user;
        }
      }
    }
    return null;
  }

  @Override
  public User getByEmail(String email) {
    if (email != null) {
      for (User user : list) {
        if (user.getEmail().equals(email)) {
          return user;
        }
      }
    }
    return null;
  }

  @Override
  public User saveOrUpdate(User user) {
    if (user != null) {
      User persisted = getById(user.getId());
      // Save
      if (persisted == null) {
        user.setId(index);
        list.add(user);
        index++;
        persisted = user;
      }
      persisted.setFirstName(user.getFirstName());
      persisted.setLastName(user.getLastName());
      persisted.setEmail(user.getEmail());
      persisted.setPassword(user.getPassword());
      persisted.setNotes(user.getNotes());
      NoteService noteService = NoteServiceImpl.getInstance();
      for (Note note : user.getNotes()) {
        noteService.saveOrUpdate(note);
      }
      return persisted;
    }
    return null;
  }

  @Override
  public boolean delete(Integer id) {
    if (id != null) {
      for (User user : list) {
        if (user.getId().equals(id)) {
          list.remove(user);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public User authenticate(String email, String password) {
    User user = UserServiceImpl.getInstance().getByEmail(email);
    if (user != null) {
      if (password.equals(user.getPassword())) {
        return user;
      }
    }
    return null;
  }

}
