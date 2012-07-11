package com.agile.spirit.notz.ws;

import java.util.List;

import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.Note;

public interface NoteResource {

  List<Note> getByUserId(Integer userId, int first, int count);

  Note getById(Integer id);

  Note save(JAXBElement<Note> webNote);

  Note update(JAXBElement<Note> webNote);

  void delete(Integer id);

}
