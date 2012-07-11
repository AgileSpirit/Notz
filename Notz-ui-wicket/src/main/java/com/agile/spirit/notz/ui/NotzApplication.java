package com.agile.spirit.notz.ui;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.agile.spirit.notz.ui.pages.user.login.LoginPage;
import com.agile.spirit.notz.ui.pages.user.signup.SignupPage;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class NotzApplication extends WebApplication {

  /*
   * APPLICATION CONFIGURATION
   */

  @Override
  public void init() {
    super.init();

    removeThreadMonitoringFromResourceWatcherForGaeSupport();

    mountPage("/notes/list", NoteListPage.class);
    mountPage("/users/form", SignupPage.class);
  }

  private void removeThreadMonitoringFromResourceWatcherForGaeSupport() {
    this.getResourceSettings().setResourcePollFrequency(null);
  }

  @Override
  public Class<? extends Page> getHomePage() {
    return LoginPage.class;
  }

  @Override
  public Session newSession(Request request, Response response) {
    return new NotzSession(request);
  }

  /*
   * WEB SERVICE CLIENT CONFIGURATION
   */

  public static final String WEB_SERVICE_URL = "http://localhost:8080/Notz-ws";

  private static final Client webServiceClient = buildWebServiceClient();

  private static final Client buildWebServiceClient() {
    ClientConfig clientConfig = new DefaultClientConfig();
    clientConfig.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
    return Client.create(clientConfig);
  }

  public Client getWebServiceclient() {
    return webServiceClient;
  }

  public static WebResource getWebResource() {
    WebResource resource = webServiceClient.resource(NotzApplication.WEB_SERVICE_URL);
    return resource;
  }

}
