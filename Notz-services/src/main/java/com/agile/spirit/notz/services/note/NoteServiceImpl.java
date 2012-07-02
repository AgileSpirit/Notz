package com.agile.spirit.notz.services.note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.user.UserServiceImpl;
import com.agile.spirit.notz.services.util.SearchCriteria;

public class NoteServiceImpl implements NoteService {

  private static NoteService instance;
  private final List<Note> list = new ArrayList<Note>();
  private Integer index = 1;

  private NoteServiceImpl() {
  }

  public static NoteService getInstance() {
    if (instance == null) {
      instance = new NoteServiceImpl();
    }
    return instance;
  }

  @Override
  public List<Note> find(SearchCriteria criteria) {
    if (criteria == null || criteria.isEmpty()) {
      return new ArrayList<Note>(list);
    }
    List<Note> result = new ArrayList<Note>();
    for (Note note : list) {
      boolean isNoteMatching = matchPattern(criteria.getPattern(), note);
      if (isNoteMatching) {
        result.add(note);
      }
    }
    return result;
  }

  private boolean matchPattern(String pattern, Note note) {
    if (pattern != null) {
      if (note.getTitle().toUpperCase().contains(pattern.toUpperCase())
          || note.getDescription().toUpperCase().contains(pattern.toUpperCase())) {
        return true;
      }
      return false;
    }
    return true;
  }

  @Override
  public Note getById(Integer id) {
    if (id != null) {
      for (Note note : list) {
        if (note.getId().equals(id)) {
          return note;
        }
      }
    }
    return null;
  }

  @Override
  public Note saveOrUpdate(Note note) {
    if (note != null) {
      Note persisted = getById(note.getId());
      // Save
      if (persisted == null) {
        note.setId(index);
        list.add(note);
        index++;
        persisted = note;
      }// Update
      persisted.setTitle(note.getTitle());
      persisted.setDescription(note.getDescription());
      return persisted;
    }
    return null;
  }

  @Override
  public boolean delete(Integer id) {
    if (id != null) {
      for (Note note : list) {
        if (note.getId().equals(id)) {
          list.remove(note);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public List<Note> findByUser(User user, int first, int count) {
    if (user != null && user.getId() != null) {
      User persistedUser = UserServiceImpl.getInstance().getById(user.getId());
      if (persistedUser != null) {
        List<Note> notes = persistedUser.getNotes();
        Collections.sort(notes);
        return notes.subList(first, first + count);
      }
    }
    return null;
  }

}
