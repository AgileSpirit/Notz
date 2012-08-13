package com.agile.spirit.notz.ui;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.agile.spirit.notz.ui.pages.home.HomePage;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.agile.spirit.notz.ui.pages.user.signup.SignupPage;

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
  public Class<? extends NotzPage> getHomePage() {
    return HomePage.class;
  }

  @Override
  public Session newSession(Request request, Response response) {
    return new NotzSession(request);
  }

}
