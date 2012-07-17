package com.agile.spirit.notz.ws;

import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.User;

public interface UserResource {

  String greeting(String firstName, String lastName);

  User login(String login, String password);

  User getById(String id);

  User saveOrUpdate(JAXBElement<User> webUser);

  void delete(String id);

  String generateUsers(int nb);

}
