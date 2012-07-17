package com.agile.spirit.notz.services;

import java.util.List;

import com.agile.spirit.notz.domain.Note;

public interface NoteService {

  List<Note> find(SearchCriteria criteria);

  List<Note> getNotesByUser(String userId, Integer first, Integer count);

  Note getById(String id);

  Note saveOrUpdate(Note note);

  void delete(String id);

}
