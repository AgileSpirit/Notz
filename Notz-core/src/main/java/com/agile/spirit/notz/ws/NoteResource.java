package com.agile.spirit.notz.ws;

import java.util.List;

import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.Note;

/**
 * 
 * @author Jérémy Buget - Agile Spirit
 * 
 */
public interface NoteResource {

  /**
   * Service used for retrieving a user list of notes.
   * 
   * @param userId
   * @param first
   * @param count
   * @return
   */
  List<Note> getByUserId(String userId, String first, String count);

  /**
   * Service used for retrieving a note by its ID.
   * 
   * @param id
   * @return
   */
  Note getById(String id);

  /**
   * Service used for persist or merge a new or existent note.
   * 
   * @param webUser
   * @return
   */
  Note saveOrUpdate(JAXBElement<Note> webNote);

  /**
   * Service used for delete an existing note.
   * 
   * @param id
   */
  void delete(String id);

}
