package com.agile.spirit.notz.ws;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class WebClientManager {

//  public static final String WEB_SERVICE_URL = "http://localhost:8080/Notz-ws";
  public static final String WEB_SERVICE_URL = "http://notzws.elasticbeanstalk.com";

  private static final Client webClient = buildWebClient();

  private static final Client buildWebClient() {
    ClientConfig clientConfig = new DefaultClientConfig();
    clientConfig.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
    Client client = Client.create(clientConfig);
//    client.addFilter(new LoggingFilter(System.out));
    return client;
  }

  /**
   * Call this method to get the WebService client.
   * @return
   */
  public static WebResource getWebResource() {
    WebResource resource = webClient.resource(WEB_SERVICE_URL);
    return resource;
  }


}
