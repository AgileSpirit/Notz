package com.agile.spirit.notz.ws;

import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.User;

public interface UserResource {

  String greeting(String firstName, String lastName);

  User login(String login, String password);

  User save(JAXBElement<User> webUser);

  User getById(Integer id);

  User update(JAXBElement<User> webUser);

  void delete(Integer id);

  String generateUsers(int nb);

}
