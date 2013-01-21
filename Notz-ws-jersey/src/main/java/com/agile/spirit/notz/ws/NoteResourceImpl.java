package com.agile.spirit.notz.ws;

import java.util.List;

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

import org.apache.log4j.Logger;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.NoteService;
import com.agile.spirit.notz.services.ServiceFactory;
import com.agile.spirit.notz.services.UserService;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/notes")
public class NoteResourceImpl extends BaseResource {

  private final static Logger LOGGER = Logger.getLogger(NoteResourceImpl.class);

  NoteService noteService;

  UserService userService;

  public NoteResourceImpl() {
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    this.noteService = serviceFactory.getNoteService();
    this.userService = serviceFactory.getUserService();
  }

  @GET
  @Path("/{userId}")
  @Produces({ "application/x-javascript", MediaType.APPLICATION_JSON })
  public JSONWithPadding read(@PathParam("userId") String userId, @QueryParam("first") String firstParam,
      @QueryParam("count") String countParam, @QueryParam("callback") String callback) {
    LOGGER.info("[GET] #read : userId=" + userId + ", first=" + firstParam + ", count=" + countParam);

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
    return new JSONWithPadding(genericNotes, callback);
  }

  @GET
  @Path("/note/{noteId}")
  @Produces({ "application/x-javascript", MediaType.APPLICATION_JSON })
  public JSONWithPadding getById(@PathParam("noteId") String noteId, @QueryParam("callback") String callback) {
    LOGGER.info("[GET] #getById : noteId=" + noteId);
    Note note = noteService.getById(noteId);
    if (note != null) {
      LOGGER.info("Note was successfully found : note = " + note.toString());
      return new JSONWithPadding(note, callback);
    }
    LOGGER.error("Note was not found for id '" + noteId + "'");
    throw new WebApplicationException();
  }

  @POST
  @Path("/{userId}")
  @Produces({ "application/x-javascript", MediaType.APPLICATION_JSON })
  public JSONWithPadding save(@PathParam("userId") String userId, Note note, @QueryParam("callback") String callback) {
    LOGGER.info("[POST] #save : userId=" + userId + ", note=" + note.toString());
    if (userId != null && note != null && note.getId() == null) {
      User user = userService.getUserById(userId);
      Note persisted = noteService.saveOrUpdate(user, note);
      if (persisted != null) {
        LOGGER.info("Note was successfully saved : note = " + note.toString());
        return new JSONWithPadding(persisted, callback);
      }
    }
    LOGGER.error("Note was not saved");
    throw new WebApplicationException();
  }

  @PUT
  @Path("/{userId}")
  @Produces({ "application/x-javascript", MediaType.APPLICATION_JSON })
  public JSONWithPadding update(@PathParam("userId") String userId, Note note, @QueryParam("callback") String callback) {
    LOGGER.info("[PUT] #update : userId=" + userId + ", note=" + note.toString());
    if (userId != null && note != null && note.getId() != null) {
      User user = userService.getUserById(userId);
      Note merged = noteService.saveOrUpdate(user, note);
      if (merged != null) {
        LOGGER.info("Note was successfully updated : note = " + note.toString());
        return new JSONWithPadding(merged, callback);
      }
    }
    LOGGER.error("Note was not updated");
    throw new WebApplicationException();
  }

  @DELETE
  @Path("/{userId}")
  public void delete(@PathParam("userId") String userId, String noteId) {
    LOGGER.info("[DELETE] #delete : userId=" + userId + ", noteId=" + noteId);
    if (userId != null && noteId != null) {
      User user = userService.getUserById(userId);
      if (user != null) {
        Note note = noteService.getById(noteId);
        if (note != null) {
          noteService.delete(user, note);
          return;
        }
      }
    }
    LOGGER.error("Note was not deleted");
    throw new WebApplicationException();
  }

}
