package com.agile.spirit.notz.ws;

import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.User;

/**
 * 
 * @author Jérémy Buget - Agile Spirit
 * 
 */
public interface UserResource {

  /**
   * Sample Service that says "Hello !"
   * 
   * @param firstName
   * @param lastName
   * @return
   */
  String greeting(String firstName, String lastName);

  /**
   * Service used for authenticate and connect a user.
   * 
   * @param login
   * @param password
   * @return
   */
  User login(String login, String password);

  /**
   * Service used for retrieving a user by its ID
   * 
   * @param id
   * @return
   */
  User getById(String id);

  /**
   * Service used for persist or merge a new or existent user.
   * 
   * @param webUser
   * @return
   */
  User saveOrUpdate(JAXBElement<User> webUser);

  /**
   * Service used for delete an existing user.
   * 
   * @param id
   */
  void delete(String id);

  /**
   * Service used for randomized users generation.
   * 
   * @param nb
   * @return
   */
  String generateUsers(int nb);

}
