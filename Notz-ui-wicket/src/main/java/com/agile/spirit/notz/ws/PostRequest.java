package com.agile.spirit.notz.ws;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public abstract class PostRequest extends AbstractWebRequest {

  @Override
  protected ClientResponse executeHttpMethod(Builder resourceBuilder) {
    if (params != null && params.size() > 0) {
      return resourceBuilder.post(ClientResponse.class, params);
    }
    return resourceBuilder.post(ClientResponse.class);
  }

}
