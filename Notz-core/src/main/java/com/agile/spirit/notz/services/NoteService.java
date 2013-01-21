package com.agile.spirit.notz.services;

import java.util.List;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;

public interface NoteService {

  List<Note> find(SearchCriteria criteria);

  Note getById(String id);

  List<Note> getNotesByUser(String userId, Integer first, Integer count);

  Note saveOrUpdate(User user, Note note);

  void delete(User user, Note note);

}
