package com.agile.spirit.notz.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.NoteService;
import com.agile.spirit.notz.services.ServiceFactory;
import com.agile.spirit.notz.services.UserService;

@Path("/notes")
public class NoteResourceImpl implements NoteResource {

  NoteService noteService;

  UserService userService;

  public NoteResourceImpl() {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    this.noteService = serviceFactory.getNoteService();
    this.userService = serviceFactory.getUserService();
  }

  @GET
  @Path("/{userId}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public List<Note> read(@PathParam("userId") String userId, @QueryParam("first") String firstParam, @QueryParam("count") String countParam) {
    Integer first = null;
    Integer count = null;
    if (firstParam != null) {
      first = new Integer(firstParam);
    }
    if (countParam != null) {
      count = new Integer(countParam);
    }
    List<Note> notes = noteService.getNotesByUser(userId, first, count);
    return notes;
  }

  @GET
  @Path("/detail/{noteId}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public Note getById(@PathParam("noteId") String noteId) {
    return noteService.getById(noteId);
  }

  @POST
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public Note save(Note note) {
    if (note != null && note.getId() == null && note.getUser() != null && note.getUser().getId() != null) {
      User user = userService.getUserById(note.getUser().getId());
      if (user != null) {
        note.setUser(user);
      }
      return noteService.saveOrUpdate(note);
    }
    return null;
  }

  @PUT
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public Note update(Note note) {
    if (note != null && note.getId() != null && note.getUser() != null && note.getUser().getId() != null) {
      User user = userService.getUserById(note.getUser().getId());
      if (user != null) {
        note.setUser(user);
      }
      return noteService.saveOrUpdate(note);
    }
    return null;
  }

  @DELETE
  @Path("/{noteId}")
  @Override
  public void delete(@PathParam("noteId") String noteId) {
    noteService.delete(noteId);
  }

}
