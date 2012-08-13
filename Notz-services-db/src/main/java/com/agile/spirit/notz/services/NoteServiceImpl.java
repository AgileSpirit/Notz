package com.agile.spirit.notz.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.util.PersistenceUtil;
import com.agile.spirit.notz.util.TransactionnalOperation;

public class NoteServiceImpl implements NoteService {

  /*
   * NAMED QUERIES
   */
  @Override
  public List<Note> getNotesByUser(final String userId, final Integer first, final Integer count) {
    Query query = PersistenceUtil.getEntityManager().createNamedQuery(Note.FIND_NOTES_BY_USER, Note.class);
    query.setParameter("userId", userId);
    List<Note> notes = query.getResultList();
    if (notes != null && first != null && count != null) {
      return notes.subList(first, first + count);
    }
    return notes;
  }

  @Override
  public List<Note> find(SearchCriteria criteria) {
    return null;
  }

  @Override
  public Note getById(String id) {
    if (id != null) {
      return PersistenceUtil.getEntityManager().find(Note.class, id);
    }
    return null;
  }

  @Override
  public Note saveOrUpdate(final Note note) {
    if (note != null) {
      return (Note) new TransactionnalOperation() {
        @Override
        public Object process(final EntityManager entityManager) {

          Note entity = getById(note.getId());
          if (entity == null) {
            entity = note;
            entity.setCreationDate(new Date());
          } else {
            entity.setTitle(note.getTitle());
            entity.setDescription(note.getDescription());
          }
          entity.setModificationDate(new Date());
          return entityManager.merge(entity);
        }
      }.execute();
    }
    return null;
  }

  @Override
  public void delete(final String id) {
    if (id != null) {
      new TransactionnalOperation() {
        @Override
        public Object process(final EntityManager entityManager) {
          Note note = entityManager.find(Note.class, id);
          if (note != null) {
            entityManager.remove(note);
            return true;
          }
          return false;
        }
      }.execute();
    }
  }

}
