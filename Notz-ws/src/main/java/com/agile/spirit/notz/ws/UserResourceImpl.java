package com.agile.spirit.notz.ws;

import java.util.Calendar;

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
import com.agile.spirit.notz.services.UserServiceImpl;

@Path("/users")
public class UserResourceImpl implements UserResource {

  @GET
  @Path("/greeting/{firstName}-{lastName}")
  @Override
  public String greeting(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
    return "Hello " + firstName + " " + lastName + " !";
  }

  @POST
  @Path("/login")
  @Produces(MediaType.APPLICATION_XML)
  @Override
  public User login(@FormParam("login") String login, @FormParam("password") String password) {
    User user = UserServiceImpl.getInstance().loginUser(login, password);
    if (user == null) {
      return null;
    } else {
      return user;
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  @Override
  public User save(JAXBElement<User> webUser) {
    User user = webUser.getValue();
    user.setCreationDate(Calendar.getInstance().getTime());
    UserServiceImpl.getInstance().saveOrUpdate(user);
    return user;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_XML)
  @Override
  public User getById(@PathParam("id") Integer id) {
    return UserServiceImpl.getInstance().getUserById(id);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  @Override
  public User update(JAXBElement<User> webUser) {
    User user = webUser.getValue();
    user.setModificationDate(Calendar.getInstance().getTime());
    UserServiceImpl.getInstance().saveOrUpdate(user);
    return user;
  }

  @DELETE
  @Path("/{id}")
  @Override
  public void delete(@PathParam("id") Integer id) {
    UserServiceImpl.getInstance().delete(id);
  }

  @GET
  @Path("/generate/{nb}")
  @Produces(MediaType.TEXT_PLAIN)
  @Override
  public String generateUsers(@PathParam("nb") int nb) {
    UserServiceImpl.getInstance().generateUsers(nb);
    return "Users generated";
  }

}
