package com.agile.spirit.notz.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.util.PersistenceUtil;
import com.agile.spirit.notz.util.TransactionnalOperation;

public class NoteServiceImpl implements NoteService {

  private static NoteService instance;

  public static NoteService getInstance() {
    if (instance == null) {
      instance = new NoteServiceImpl();
    }
    return instance;
  }

  /*
   * NAMED QUERIES
   */
  public List<Note> getNotesByUser(final Integer userId, final int first, final int count) {
    List<Note> notes = PersistenceUtil.getEntityManager().createNamedQuery(Note.FIND_NOTES_BY_USER, Note.class).getResultList();
    return notes;
  }

  @Override
  public List<Note> find(SearchCriteria criteria) {
    return null;
  }

  @Override
  public Note getById(Integer id) {
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
        public Object processInTransaction(final EntityManager entityManager) {
          if (note.getId() == null) {
            note.setCreationDate(new Date());
            entityManager.persist(note);
            return note;
          } else {
            note.setModificationDate(new Date());
            return entityManager.merge(note);
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

  @Override
  public List<Note> findNotesByUser(User user, int first, int count) {
    if (user != null && user.getId() != null) {
      User persistedUser = UserServiceImpl.getInstance().getUserById(user.getId());
      if (persistedUser != null) {
        List<Note> notes = persistedUser.getNotes();
        Collections.sort(notes);
        return notes.subList(first, first + count);
      }
    }
    return null;
  }

}
