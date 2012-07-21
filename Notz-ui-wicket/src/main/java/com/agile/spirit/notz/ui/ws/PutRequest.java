package com.agile.spirit.notz.ui.ws;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public abstract class PutRequest extends WebResourceRequest {

  @Override
  protected ClientResponse executeHttpMethod(Builder resourceBuilder) {
    if (params != null && params.size() > 0) {
      return resourceBuilder.put(ClientResponse.class, params);
    }
    return resourceBuilder.put(ClientResponse.class);
  }

}
