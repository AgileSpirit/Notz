package com.agile.spirit.notz.ws;

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
   * Service used for retrieving a user by its ID, email or username.
   * 
   * @param expression
   * @return
   */
  User getUser(String expression);

  /**
   * Service used for persist a new user.
   * 
   * @param webUser
   * @return
   */
  User save(User webUser);

  /**
   * Service used for merge an existent user.
   * 
   * @param webUser
   * @return
   */
  User update(User webUser);

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
