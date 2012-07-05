package com.agile.spirit.notz.util;

import javax.persistence.EntityManager;

/**
 * Use this class to manage database transaction.
 * 
 * @author Jérémy Buget
 *
 */
public abstract class TransactionnalOperation {

  /**
   * Method to override in order to interact with the database through the transaction EntityManager
   * 
   * @param entityManager
   * @return
   */
  public abstract Object processInTransaction(final EntityManager entityManager);

  /**
   * <ol>
   * <li>Retrieve an EntityManager</li>
   * <li>Open a transaction</li>
   * <li>Run the <i>processInTransaction</i> method</li>
   * <li>Commit the transaction ; Rollback if necessary</li>
   * <li>Close the EntityManager</li>
   * </ol>
   * @return the result Object returned by the overriden <i>processInTransaction</i> method
   */
  public Object execute() {
    Object result = null;
    EntityManager entityManager = PersistenceUtil.getEntityManager();
    try {
      entityManager.getTransaction().begin();
      result = processInTransaction(entityManager);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
    } finally {
      entityManager.close();
    }
    return result;
  }

}
