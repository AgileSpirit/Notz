package com.agile.spirit.notz.services;

import java.util.List;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;


public interface NoteService {

  List<Note> find(SearchCriteria criteria);

  List<Note> findNotesByUser(User user, int first, int count);
  
  Note getById(Integer id);
  
  Note saveOrUpdate(Note note);

  void delete(Integer id);
  
}
