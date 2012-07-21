package com.agile.spirit.notz.ui.ws;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

public abstract class GetRequest extends WebResourceRequest {

  @Override
  protected ClientResponse executeHttpMethod(Builder resourceBuilder) {
    return resourceBuilder.get(ClientResponse.class);
  }

}
