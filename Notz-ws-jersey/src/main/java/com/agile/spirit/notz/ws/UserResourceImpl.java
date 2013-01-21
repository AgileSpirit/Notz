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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.ServiceFactory;
import com.agile.spirit.notz.services.UserService;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/users")
@Produces({ "application/x-javascript", MediaType.APPLICATION_JSON })
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
  public Response login(@FormParam("login") String login, @FormParam("password") String password, @QueryParam("callback") String callback) {
    LOGGER.info("[POST] #login : login=" + login + ", password=" + password);
    User user = userService.loginUser(login, password);
    if (user != null) {
      return getResponseOk(new JSONWithPadding(user, callback));
    }
    LOGGER.error("User was not logged in");
    throw new WebApplicationException();
  }

  @POST
  public Response save(User user, @QueryParam("callback") String callback) {
    LOGGER.info("[POST] #save : user=" + user.toString());
    User persisted = userService.saveOrUpdate(user);
    if (persisted != null) {
      LOGGER.info("User was saved : user = " + user.toString());
      return getResponseOk(new JSONWithPadding(persisted, callback));
    }
    LOGGER.error("User was not saved");
    throw new WebApplicationException();
  }

  @PUT
  public Response update(User user, @QueryParam("callback") String callback) {
    LOGGER.info("[PUT] #update: user=" + user.toString());
    User merged = userService.saveOrUpdate(user);
    if (merged != null) {
      return getResponseOk(new JSONWithPadding(merged, callback));
    }
    LOGGER.error("User was not updated");
    throw new WebApplicationException();
  }

  @GET
  @Path("/{expression}")
  public Response getUser(@PathParam("expression") String expression, @QueryParam("callback") String callback) {
    LOGGER.info("Get user matching expression '" + expression + "'");
    List<User> users = userService.findUser(expression);

    if (users == null || users.isEmpty()) {
      throw new WebApplicationException();
    }

    return getResponseOk(new JSONWithPadding(users.get(0), callback));
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
  public Response listUsers() {
    List<User> users = userService.listUsers();
    GenericEntity<List<User>> genericUsers = new GenericEntity<List<User>>(users) {
    };
    return getResponseOk(new JSONWithPadding(genericUsers));
  }

}
