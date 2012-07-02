package com.agile.spirit.notz.ui;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.agile.spirit.notz.services.user.UserServiceImpl;
import com.agile.spirit.notz.services.util.DataGenerator;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.agile.spirit.notz.ui.pages.user.form.UserFormPage;
import com.agile.spirit.notz.ui.pages.user.login.LoginPage;

public class NotzApplication extends WebApplication {

  public NotzApplication() {
    super();
    if (UserServiceImpl.getInstance().find(null).size() == 0) {
      DataGenerator.generateData();
    }
  }
  
  @Override
  public void init() {
    super.init();

    removeThreadMonitoringFromResourceWatcherForGaeSupport();
    
    mountPage("/notes/list", NoteListPage.class);
    mountPage("/users/form", UserFormPage.class);
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

}
