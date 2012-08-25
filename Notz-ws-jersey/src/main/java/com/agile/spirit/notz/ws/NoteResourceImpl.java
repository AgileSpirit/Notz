package com.agile.spirit.notz.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.services.NoteService;
import com.agile.spirit.notz.services.ServiceFactory;

@Path("/notes")
public class NoteResourceImpl implements NoteResource {

  NoteService noteService;

  public NoteResourceImpl() {
    this.noteService = ServiceFactory.getInstance().getNoteService();
  }

  @GET
  @Path("/{userId}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public List<Note> getByUserId(@PathParam("userId") String userId, @QueryParam("first") String firstParam,
      @QueryParam("count") String countParam) {
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
  @Path("/detail/{id}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public Note getById(@PathParam("id") String id) {
    return noteService.getById(id);
  }

  @PUT
  @Consumes({ MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public Note saveOrUpdate(JAXBElement<Note> webNote) {
    Note note = webNote.getValue();
    noteService.saveOrUpdate(note);
    return note;
  }

  @DELETE
  @Path("/{id}")
  @Override
  public void delete(@PathParam("id") String id) {
    noteService.delete(id);
  }

}
