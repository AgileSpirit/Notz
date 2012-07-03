package com.agile.spirit.notz.ws;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/user")
public class UserResource {

  @POST
  @Path("/login/{}")
  public void login(String email, String password) {
    
  }
  
  @POST
  public void save() {
  }
  
  @GET
  @Path("{id}")
  public void getById(@PathParam("id") Integer id) {
    
  }
  
  @PUT
  @Path("{id}")
  public void update() {
    
  }
  
  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") Integer id) {
    
  }
  
}
