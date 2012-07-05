package com.agile.spirit.notz.ui;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.util.SearchCriteria;

public class NotzSession extends WebSession {

  private static final long serialVersionUID = 1L;

  private User user = null;
  private SearchCriteria userSearchCriteria = null;
  private SearchCriteria noteSearchCriteria = null;

  public NotzSession(Request request) {
    super(request);
    userSearchCriteria = new SearchCriteria();
    noteSearchCriteria = new SearchCriteria();
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public SearchCriteria getUserSearchCriteria() {
    return userSearchCriteria;
  }

  public void setUserSearchCriteria(SearchCriteria userSearchCriteria) {
    this.userSearchCriteria = userSearchCriteria;
  }

  public SearchCriteria getNoteSearchCriteria() {
    return noteSearchCriteria;
  }

  public void setNoteSearchCriteria(SearchCriteria noteSearchCriteria) {
    this.noteSearchCriteria = noteSearchCriteria;
  }

}
