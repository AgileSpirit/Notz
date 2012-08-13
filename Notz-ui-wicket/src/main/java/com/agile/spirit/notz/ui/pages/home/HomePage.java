package com.agile.spirit.notz.ui.pages.home;

import java.net.CookieManager;

import javax.ws.rs.core.Cookie;

import org.apache.wicket.util.cookies.CookieDefaults;
import org.apache.wicket.util.cookies.CookieUtils;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.note.list.NoteList;
import com.agile.spirit.notz.ui.components.user.login.LoginForm;
import com.agile.spirit.notz.ui.components.user.signup.SignupForm;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;

public class HomePage extends NotzPage {

  private static final long serialVersionUID = 1L;

  public HomePage() {
    super();
    buildOverview();
    buildLoginForm();
    buildSignupForm();
  }

  @Override
  public void onInitialize() {
    super.onInitialize();
    
    User user = getNotzSession().getUser();
    if (user != null) {
      setResponsePage(NoteListPage.class);
    }
    
    // TODO Manage cookie / RememberMe feature
  }
  
  private void buildOverview() {
    // TODO Auto-generated method stub

  }

  private void buildLoginForm() {
    add(new LoginForm("loginForm"));
  }

  private void buildSignupForm() {
    add(new SignupForm("signupForm"));

  }

  @Override
  protected void doOnInitialize() {
    /*
     * DO NOTHING
     */
  }

}
