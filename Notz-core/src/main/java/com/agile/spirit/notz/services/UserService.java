package com.agile.spirit.notz.services;

import com.agile.spirit.notz.domain.User;

public interface UserService {

  User getUserById(Integer id);

  User saveOrUpdate(User user);

  void delete(Integer id);

  User loginUser(String email, String password);

  void generateUsers(int nb);

}
