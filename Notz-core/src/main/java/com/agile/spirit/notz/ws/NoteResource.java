package com.agile.spirit.notz.ws;

import java.util.List;

import com.agile.spirit.notz.domain.Note;

/**
 * 
 * @author Jérémy Buget - Agile Spirit
 * 
 */
public interface NoteResource {

  /**
   * Service used for retrieving a note by its ID.
   * 
   * @param id
   * @return
   */
  Note getById(String noteId);

  /**
   * Service used for retrieving a user list of notes.
   * 
   * @param userId
   * @param first
   * @param count
   * @return
   */
  List<Note> read(String userId, String first, String count);

  /**
   * Service used for persist a new Note.
   * 
   * @param webUser
   * @return
   */
  Note save(Note note);

  /**
   * Service used for merge an existing Note.
   * 
   * @param webUser
   * @return
   */
  Note update(Note note);

  /**
   * Service used for delete an existing note.
   * 
   * @param id
   */
  void delete(String id);

}
