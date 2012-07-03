package com.agile.spirit.notz.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.user.UserServiceImpl;

@Path("/users")
public class UserResource {

  @GET
  @Path("/greeting/{firstName}-{lastName}")
  public String greeting(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
    return "Hello " + firstName + " " + lastName + " !";
  }
  
  @POST
  @Path("/login")
  @Produces(MediaType.APPLICATION_XML)
  public User login(@FormParam("email") String email, @FormParam("password")String password) {
    User user = UserServiceImpl.getInstance().authenticate(email, password);
    if (user == null) {
      return null;
    } else {
      return user;
    }
  }
  
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public User save(JAXBElement<User> webUser) {
    User user = webUser.getValue();
    UserServiceImpl.getInstance().saveOrUpdate(user);
    return user;
  }
  
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_XML)
  public User getById(@PathParam("id") Integer id) {
    return UserServiceImpl.getInstance().getById(id);
  }
  
  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public User update(JAXBElement<User> webUser) {
    User user = webUser.getValue();
    UserServiceImpl.getInstance().saveOrUpdate(user);
    return user;
  }
  
  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") Integer id) {
    UserServiceImpl.getInstance().delete(id);
  }
  
}
