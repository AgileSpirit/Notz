package com.agile.spirit.notz.services;

import com.agile.spirit.notz.domain.User;

public interface UserService {

  User getUserById(String id);

  User saveOrUpdate(User user);

  void delete(String id);

  User loginUser(String login, String password);

  void generateUsers(int nb);

}
