package com.agile.spirit.notz.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

  public static String PERSISTENCE_UNIT_NAME = "NotzPU";

  private static final EntityManagerFactory emf = buildEntityManagerFactory();

  private static EntityManagerFactory buildEntityManagerFactory() {
    return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  }

  public static EntityManager getEntityManager() {
    return emf.createEntityManager();
  }
}
