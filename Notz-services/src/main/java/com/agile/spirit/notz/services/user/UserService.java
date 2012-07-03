package com.agile.spirit.notz.services.user;

import java.util.List;


import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.util.SearchCriteria;

public interface UserService {

  List<User> find(SearchCriteria criteria);

  User getById(Integer id);

  User saveOrUpdate(User user);

  boolean delete(Integer id);
  
  User authenticate(String email, String password);

  User getByEmail(String email);

}
