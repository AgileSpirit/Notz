package com.agile.spirit.notz.security;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class ResponseCorsFilter implements ContainerResponseFilter {

  private static final Logger LOGGER = Logger.getLogger(ResponseCorsFilter.class);

  @Override
  public ContainerResponse filter(ContainerRequest req, ContainerResponse contResp) {

    LOGGER.info("Enter CORS filter");
    LOGGER.info("Request= { path:" + req.getPath() + ", method:" + req.getMethod() + " }");

    ResponseBuilder resp = Response.fromResponse(contResp.getResponse());
    resp.header("Access-Control-Allow-Origin", "*");
    resp.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

    String reqHead = req.getHeaderValue("Access-Control-Request-Headers");

    if (null != reqHead && !reqHead.equals(null)) {
      resp.header("Access-Control-Allow-Headers", reqHead);
    }

    contResp.setResponse(resp.build());

    LOGGER.info("Exit CORS filter");

    return contResp;
  }
}
