package com.agile.spirit.notz.ui.pages.user.login;

import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.user.login.LoginForm;

public class LoginPage extends NotzPage {

  private static final long serialVersionUID = 1L;

  public LoginPage() {
    super();
    buildLoginForm();
  }

  private void buildLoginForm() {
    add(new LoginForm("loginForm"));
  }

  @Override
  protected void doOnInitialize() {
    /*
     * DO NOTHING
     */
  }

}
