package com.agile.spirit.notz.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.util.MemoryCache;

public class NoteServiceImpl implements NoteService {

  MemoryCache memoryCache;
  UserService userService;

  public NoteServiceImpl() {
    this.memoryCache = MemoryCache.getInstance();
    this.userService = new UserServiceImpl();
  }

  @Override
  public List<Note> find(SearchCriteria criteria) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Note> getNotesByUser(String userId, Integer first, Integer count) {
    for (User user : memoryCache.getData().values()) {
      if (user.getId().equals(userId)) {
        if (user.getNotes() == null) {
          return Collections.emptyList();
        }
        if (first == null || count == null) {
          return user.getNotes();
        }
        return user.getNotes().subList(first, first + count);
      }
    }
    return Collections.emptyList();
  }

  @Override
  public Note getById(String id) {
    for (User user : memoryCache.getData().values()) {
      for (Note note : user.getNotes()) {
        if (note.getId().equals(id)) {
          return note;
        }
      }
    }
    return null;
  }

  @Override
  public Note saveOrUpdate(User user, Note note) {
    if (user != null) {
      if (note.getId() == null) {
        return saveNote(user, note);
      }
      return updateNote(user, note);
    }
    return null;
  }

  private Note saveNote(User user, Note note) {
    note.setId(memoryCache.getNextVal());
    note.setCreationDate(new Date());
    note.setModificationDate(note.getCreationDate());
    user.addNote(note);
    return note;
  }

  private Note updateNote(User user, Note note) {
    for (Note pNote : user.getNotes()) {
      if (pNote.getId().equals(note.getId())) {
        pNote.setTitle(note.getTitle());
        pNote.setDescription(note.getDescription());
        pNote.setModificationDate(new Date());
        return pNote;
      }
    }
    return saveNote(user, note);
  }

  @Override
  public void delete(User user, Note note) {
    if (user != null) {
      user.removeNote(note);
    }
  }

}
