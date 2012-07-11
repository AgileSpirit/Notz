package com.agile.spirit.notz.ui.pages.user.signup;

import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.user.form.UserRegistrationForm;

public class SignupPage extends NotzPage {

  private static final long serialVersionUID = 1L;

  public SignupPage() {
    super();
    buildUserForm();
  }

  @Override
  protected void doOnInitialize() {
    /*
     * DO NOTHING
     */
  }

  private void buildUserForm() {
    add(new UserRegistrationForm("userForm"));
  }

}
