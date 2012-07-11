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
import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.services.NoteServiceImpl;

@Path("/notes")
public class NoteResourceImpl implements NoteResource {

  @GET
  @Path("{userId}")
  @Produces(MediaType.APPLICATION_XML)
  @Override
  public List<Note> getByUserId(@PathParam("userId") Integer userId, @QueryParam("first") String firstParam,
      @QueryParam("count") String countParam) {
    Integer first = null;
    Integer count = null;
    if (firstParam != null) {
      first = new Integer(firstParam);
    }
    if (countParam != null) {
      count = new Integer(countParam);
    }
    List<Note> notes = NoteServiceImpl.getInstance().getNotesByUser(userId, first, count);
    return notes;
  }

  @GET
  @Path("/detail/{id}")
  @Produces(MediaType.APPLICATION_XML)
  @Override
  public Note getById(@PathParam("id") Integer id) {
    return NoteServiceImpl.getInstance().getById(id);
  }

  @Override
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Note save(JAXBElement<Note> webNote) {
    Note note = webNote.getValue();
    NoteServiceImpl.getInstance().saveOrUpdate(note);
    return note;
  }

  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  @Override
  public Note update(JAXBElement<Note> webNote) {
    Note note = webNote.getValue();
    NoteServiceImpl.getInstance().saveOrUpdate(note);
    return note;
  }

  @DELETE
  @Path("/{id}")
  @Override
  public void delete(@PathParam("id") Integer id) {
    NoteServiceImpl.getInstance().delete(id);
  }

}
