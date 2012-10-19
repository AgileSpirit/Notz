package com.agile.spirit.notz.ws;

import java.util.List;

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

import org.apache.log4j.Logger;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.ServiceFactory;
import com.agile.spirit.notz.services.UserService;

@Path("/users")
public class UserResourceImpl implements UserResource {

  private final static Logger LOGGER = Logger.getLogger(UserResourceImpl.class);

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
    LOGGER.info("Login user with login '" + login + "' and password " + password + "'");
    User user = userService.loginUser(login, password);
    if (user == null) {
      return null;
    }
    return user;
  }

  @POST
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public User save(User user) {
    LOGGER.info("Save user " + user.toString());
    return userService.saveOrUpdate(user);
  }

  @PUT
  @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public User update(User user) {
    LOGGER.info("Update user " + user.toString());
    return userService.saveOrUpdate(user);
  }

  @GET
  @Path("/{expression}")
  @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
  @Override
  public User getUser(@PathParam("expression") String expression) {
    LOGGER.info("Get user matching expression '" + expression + "'");
    List<User> users = userService.findUser(expression);
    if (users != null && !users.isEmpty()) {
      return users.get(0);
    }
    return null;
  }

  @DELETE
  @Path("/{id}")
  @Override
  public void delete(@PathParam("id") String id) {
    LOGGER.info("Delete user with id '" + id + "'");
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
