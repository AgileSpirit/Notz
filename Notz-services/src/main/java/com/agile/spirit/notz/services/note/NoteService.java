package com.agile.spirit.notz.services.note;

import java.util.List;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.util.SearchCriteria;

public interface NoteService {

  List<Note> find(SearchCriteria criteria);

  List<Note> findByUser(User user, int first, int count);
  
  Note getById(Integer id);
  
  Note saveOrUpdate(Note note);

  boolean delete(Integer id);
  
}
