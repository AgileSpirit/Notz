package com.agile.spirit.notz.ws;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public abstract class GetRequest extends AbstractWebRequest {

  @Override
  protected ClientResponse executeHttpMethod(Builder resourceBuilder) {
    return resourceBuilder.get(ClientResponse.class);
  }

}
