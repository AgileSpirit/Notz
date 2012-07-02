package com.agile.spirit.notz.ui.components.user.form;

import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.User;

public class UserRegistrationForm extends UserForm {

  private static final long serialVersionUID = -7973296164741028206L;

  public UserRegistrationForm(String id, IModel<User> user) {
    super(id, user);
  }

  @Override
  protected String getTitleKey() {
    return "registration";
  }

  @Override
  protected String getValidateButtonKey() {
    return "create";
  }

  @Override
  protected boolean isCreationMode() {
    return true;
  }

  @Override
  protected boolean isGtuSectionVisible() {
    return true;
  }

}
