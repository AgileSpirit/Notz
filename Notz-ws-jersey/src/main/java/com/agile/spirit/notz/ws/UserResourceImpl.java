package com.agile.spirit.notz.ws;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.ServiceFactory;
import com.agile.spirit.notz.services.UserService;

@Path("/users")
public class UserResourceImpl extends BaseResource {

  private final static Logger LOGGER = Logger.getLogger(UserResourceImpl.class);

  UserService userService;

  public UserResourceImpl() {
    this.userService = ServiceFactory.getInstance().getUserService();
  }

  @GET
  @Path("/greeting/{firstName}-{lastName}")
  public String greeting(@PathParam("firstName") String firstName, @PathParam("lastName") String lastName) {
    return "Hello " + firstName + " " + lastName + " !";
  }

  /**
   * Service to authenticate a user.
   * 
   * @param login
   * @param password
   * @return
   */
  @POST
  @Path("/login")
  public Response login(@FormParam("login") String login, @FormParam("password") String password) {
    LOGGER.info("Login user with login '" + login + "' and password " + password + "'");
    User user = userService.loginUser(login, password);
    if (user == null) {
      throw new WebApplicationException();
    }

    Response response = getResponseOk(user);
    return response;
  }

  @POST
  public Response save(User user) {
    LOGGER.info("Save user " + user.toString());

    User persisted = userService.saveOrUpdate(user);
    if (persisted == null) {
      throw new WebApplicationException();
    }

    Response response = getResponseOk(persisted);
    return response;
  }

  @PUT
  public Response update(User user) {
    LOGGER.info("Update user " + user.toString());
    User merged = userService.saveOrUpdate(user);
    if (merged == null) {
      throw new WebApplicationException();
    }

    Response response = getResponseOk(merged);
    return response;
  }

  @GET
  @Path("/{expression}")
  public Response getUser(@PathParam("expression") String expression) {
    LOGGER.info("Get user matching expression '" + expression + "'");
    List<User> users = userService.findUser(expression);
    if (users == null || users.isEmpty()) {
      throw new WebApplicationException();
    }

    Response response = getResponseOk(users.get(0));
    return response;
  }

  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") String id) {
    LOGGER.info("Delete user with id '" + id + "'");
    userService.delete(id);
  }

  @GET
  @Path("/generate/{nb}")
  @Produces(MediaType.TEXT_PLAIN)
  public String generateUsers(@PathParam("nb") int nb) {
    userService.generateUsers(nb);
    return "Users generated";
  }

  @GET
  @Path("/list")
  public Response listJSON() {
    LOGGER.info("List (JSON) all users");

    // Retrieve users
    List<User> users = userService.listUsers();

    GenericEntity<List<User>> genericUsers = new GenericEntity<List<User>>(users) {
    };
    LOGGER.info("Retrieved users = " + genericUsers);

    // Build & return Response
    Response response = getResponseOk(genericUsers);
    return response;
  }

}
