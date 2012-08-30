package com.agile.spirit.notz.ws;

import static com.sun.jersey.api.client.ClientResponse.Status.ACCEPTED;
import static com.sun.jersey.api.client.ClientResponse.Status.CREATED;
import static com.sun.jersey.api.client.ClientResponse.Status.OK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public abstract class AbstractWebRequest {

  private static final Logger LOGGER = Logger.getLogger(AbstractWebRequest.class);

  /**
   * Implement this method to configure the WebResource (path, URI or body parameters, accepted data type, etc.)
   * 
   * @param webResource
   * @return
   */
  public abstract WebResource.Builder configureWebResource(final WebResource webResource);

  /**
   * Executed treatment if the request sending and response receiving succeeded.
   * 
   * @param response
   */
  public void onSuccess(final ClientResponse response) {
    // BY DEFAULT DO NOTHING
  }

  /**
   * Executed treatment if an error occurred during data (un-)marshalling, request sending, WebResource processing.
   * 
   * @param response
   */
  public void onError(final ClientResponse response) {
    System.err.println("An error occurred with status: " + response.getClientResponseStatus().toString());
  }

  protected abstract ClientResponse executeHttpMethod(WebResource.Builder resourceBuilder);

  protected MultivaluedMap<String, String> params = new MultivaluedMapImpl();

  private List<Status> validStatus = null;

  public AbstractWebRequest() {
    super();
    setDefaultValidStatus();
  }

  /**
   * Override this method in order to add or remove ClientResponse status considered as valid (for this request only).
   * 
   * @return
   */
  public void setValidStatus(List<Status> status) {
    validStatus = new ArrayList<Status>(status);
  }

  /**
   * Send the request to the WebServer
   * 
   * @return
   */
  public final ClientResponse execute() {
    final WebResource webResource = WebClientManager.getWebResource();
    ClientResponse response = executeHttpMethod(configureWebResource(webResource));

    if (isResponseValid(response)) {
      onSuccess(response);
    } else {
      onError(response);
    }
    return response;
  }

  /**
   * By default, ClientResponse status considered as valid are: OK (200), CREATED (201) and ACCEPTED (202). You can change this default list
   * by using method setValidStatus(List\<Status\>).
   */
  private final void setDefaultValidStatus() {
    Status[] status = { OK, CREATED, ACCEPTED };
    validStatus = Arrays.asList(status);
  }

  /**
   * Check if the status code contained in the response is valid.
   * 
   * @param response
   * @return
   */
  private boolean isResponseValid(final ClientResponse response) {
    for (Status status : validStatus) {
      if (response.getClientResponseStatus() == status) {
        return true;
      }
    }
    return false;
  }

  public void addParam(final String key, final String value) {
    this.params.add(key, value);
  }

}
