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
import com.agile.spirit.notz.services.ServiceFactory;
import com.agile.spirit.notz.services.UserService;

@Path("/users")
public class UserResourceImpl implements UserResource {

  UserService userService;

  public UserResourceImpl() {
    this.userService = ServiceFactory.getInstance().getUserService();
  }

  @GET
  @Path("/greeting/{firstName}-{lastName}")
  @Override
  public String greeting(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
    return "Hello " + firstName + " " + lastName + " !";
  }

  @POST
  @Path("/login")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public User login(@FormParam("login") String login, @FormParam("password") String password) {
    User user = userService.loginUser(login, password);
    if (user == null) {
      return null;
    }
    return user;
  }

  @PUT
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public User saveOrUpdate(JAXBElement<User> webUser) {
    User user = webUser.getValue();
    userService.saveOrUpdate(user);
    return user;
  }

  @GET
  @Path("/{id}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public User getById(@PathParam("id") String id) {
    return userService.getUserById(id);
  }

  @DELETE
  @Path("/{id}")
  @Override
  public void delete(@PathParam("id") String id) {
    userService.delete(id);
  }

  @GET
  @Path("/generate/{nb}")
  @Produces(MediaType.TEXT_PLAIN)
  @Override
  public String generateUsers(@PathParam("nb") int nb) {
    userService.generateUsers(nb);
    return "Users generated";
  }

}
