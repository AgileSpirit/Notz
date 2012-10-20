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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.NoteService;
import com.agile.spirit.notz.services.ServiceFactory;
import com.agile.spirit.notz.services.UserService;

@Path("/notes")
public class NoteResourceImpl extends BaseResource {

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
  public Response read(@PathParam("userId") String userId, @QueryParam("first") String firstParam, @QueryParam("count") String countParam) {
    Integer first = null;
    Integer count = null;
    if (firstParam != null) {
      first = new Integer(firstParam);
    }
    if (countParam != null) {
      count = new Integer(countParam);
    }
    List<Note> notes = noteService.getNotesByUser(userId, first, count);

    GenericEntity<List<Note>> genericNotes = new GenericEntity<List<Note>>(notes) {
    };

    Response response = getResponseOk(genericNotes);
    return response;
  }

  @GET
  @Path("/detail/{noteId}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  public Response getById(@PathParam("noteId") String noteId) {
    Note note = noteService.getById(noteId);
    if (note == null) {
      throw new WebApplicationException();
    }
    Response response = getResponseOk(note);
    return response;
  }

  @POST
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
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
  public void delete(@PathParam("noteId") String noteId) {
    noteService.delete(noteId);
  }

}
