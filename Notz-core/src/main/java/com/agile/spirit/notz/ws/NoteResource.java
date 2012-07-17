package com.agile.spirit.notz.ws;

import java.util.List;

import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.Note;

public interface NoteResource {

  List<Note> getByUserId(String userId, String first, String count);

  Note getById(String id);

  Note saveOrUpdate(JAXBElement<Note> webNote);

  void delete(String id);

}
