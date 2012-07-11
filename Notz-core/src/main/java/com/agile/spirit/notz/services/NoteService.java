package com.agile.spirit.notz.services;

import java.util.List;

import com.agile.spirit.notz.domain.Note;

public interface NoteService {

  List<Note> find(SearchCriteria criteria);

  List<Note> getNotesByUser(Integer userId, Integer first, Integer count);

  Note getById(Integer id);

  Note saveOrUpdate(Note note);

  void delete(Integer id);

}
