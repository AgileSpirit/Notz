package com.agile.spirit.notz.services;

import java.util.List;

import com.agile.spirit.notz.domain.User;

public interface UserService {

  User getUserById(String id);

  List<User> findUser(String expression);

  User saveOrUpdate(User user);

  void delete(String id);

  User loginUser(String login, String password);

  void generateUsers(int nb);

}
