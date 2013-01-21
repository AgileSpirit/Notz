package com.agile.spirit.notz.ws;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

public abstract class BaseResource {

  protected Response getResponseOk(Object entity) {
    ResponseBuilder responseBuilder = Response.ok(entity);
    responseBuilder.header("Access-Control-Allow-Origin", "*");
    responseBuilder.header("Access-Control-Requet-Method", "GET,HEAD,POST,PUT,OPTIONS");
    return responseBuilder.build();
  }

  protected Response getResponseNoContent() {
    ResponseBuilder responseBuilder = Response.noContent();
    responseBuilder.header("Access-Control-Allow-Origin", "*");
    return responseBuilder.build();
  }

}
