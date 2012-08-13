package com.agile.spirit.notz.ui.components.user.form;

import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.User;

public class UserRegistrationForm extends UserForm {

  private static final long serialVersionUID = 1L;

  public UserRegistrationForm(String id) {
    super(id, new Model<User>(User.create()));
  }

  @Override
  protected String getTitleKey() {
    return "registration";
  }

}
