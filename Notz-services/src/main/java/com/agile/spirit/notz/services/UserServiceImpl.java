package com.agile.spirit.notz.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.util.DataGenerator;
import com.agile.spirit.notz.util.PersistenceUtil;
import com.agile.spirit.notz.util.TransactionnalOperation;

public class UserServiceImpl implements UserService {

  private static UserService instance;

  public static UserService getInstance() {
    if (instance == null) {
      instance = new UserServiceImpl();
    }
    return instance;
  }

  /*
   * NAMED QUERIES
   */
  public User getUserByEmail(String email) {
    User user = PersistenceUtil.getEntityManager().createNamedQuery(User.FIND_USERS_BY_EMAIL, User.class).setParameter("email", email)
        .getResultList().get(0);
    return user;
  }

  @Override
  public User getUserById(Integer id) {
    return PersistenceUtil.getEntityManager().find(User.class, id);
  }

  @Override
  public User saveOrUpdate(final User user) {
    if (user != null) {
      return (User) new TransactionnalOperation() {
        @Override
        public Object processInTransaction(final EntityManager entityManager) {
          if (user.getId() == null) {
            user.setCreationDate(new Date());
            entityManager.persist(user);
            return user;
          } else {
            user.setModificationDate(new Date());
            return entityManager.merge(user);
          }
        }
      }.execute();
    }
    return null;
  }

  @Override
  public void delete(final Integer id) {
    if (id != null) {
      new TransactionnalOperation() {
        @Override
        public Object processInTransaction(final EntityManager entityManager) {
          User user = entityManager.find(User.class, id);
          if (user != null) {
            entityManager.remove(user);
            return true;
          }
          return false;
        }
      }.execute();
    }
  }

  @Override
  public User loginUser(String email, String password) {
    if (email != null && password != null) {
      User user = getUserByEmail(email);
      if (user != null) {
        if (user.getPassword().equals(password)) {
          return user;
        }
      }
    }
    return null;
  }

  @Override
  public void generateUsers(int nb) {
    List<User> users = DataGenerator.generateUsers(nb);
    users.add(DataGenerator.generateAdminUser(false));
    for (User user : users) {
      user.setNotes(DataGenerator.generateNotes(5));
      saveOrUpdate(user);
    }
  }

}
