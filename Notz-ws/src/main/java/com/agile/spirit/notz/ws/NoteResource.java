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
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.note.NoteServiceImpl;
import com.agile.spirit.notz.services.user.UserServiceImpl;

@Path("/notes")
public class NoteResource {

  @GET
  @Path("{userId}")
  @Produces(MediaType.APPLICATION_XML)
  public List<Note> getByUserId(@PathParam("userId") Integer userId) {
    User user = UserServiceImpl.getInstance().getById(userId);
    if (user != null) {
      return user.getNotes();
    }
    return null;
  }
  
  @GET
  @Path("/detail/{id}")
  @Produces(MediaType.APPLICATION_XML)
  public Note getById(@PathParam("id") Integer id) {
    return NoteServiceImpl.getInstance().getById(id);
  }
  
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
  public Note update(JAXBElement<Note> webNote) {
    Note note = webNote.getValue();
    NoteServiceImpl.getInstance().saveOrUpdate(note);
    return note;
  }
  
  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") Integer id) {
    NoteServiceImpl.getInstance().delete(id);
  }
  
}
