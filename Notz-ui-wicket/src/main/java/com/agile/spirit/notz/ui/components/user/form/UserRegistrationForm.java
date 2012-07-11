package com.agile.spirit.notz.ui.components.user.form;

import org.apache.wicket.model.CompoundPropertyModel;

import com.agile.spirit.notz.domain.User;

public class UserRegistrationForm extends UserForm {

  private static final long serialVersionUID = 1L;

  public UserRegistrationForm(String id) {
    super(id);
  }

  @Override
  protected void buildModel() {
    this.model = new CompoundPropertyModel<User>(User.create());
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
