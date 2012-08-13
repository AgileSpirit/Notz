package com.agile.spirit.notz.ws;

import static com.sun.jersey.api.client.ClientResponse.Status.ACCEPTED;
import static com.sun.jersey.api.client.ClientResponse.Status.NO_CONTENT;
import static com.sun.jersey.api.client.ClientResponse.Status.OK;

import java.util.Arrays;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource.Builder;

public abstract class DeleteRequest extends AbstractWebRequest {

  public DeleteRequest() {
    super();

    Status[] status = { OK, ACCEPTED, NO_CONTENT };
    setValidStatus(Arrays.asList(status));
  }

  @Override
  protected ClientResponse executeHttpMethod(Builder resourceBuilder) {
    return resourceBuilder.delete(ClientResponse.class);
  }

}
