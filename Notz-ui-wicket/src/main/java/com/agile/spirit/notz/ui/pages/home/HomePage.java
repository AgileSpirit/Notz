package com.agile.spirit.notz.ui.pages.home;

import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.user.login.LoginForm;
import com.agile.spirit.notz.ui.components.user.signup.SignupForm;

public class HomePage extends NotzPage {

  private static final long serialVersionUID = 1L;

  public HomePage() {
    super();
    buildOverview();
    buildLoginForm();
    buildSignupForm();
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
