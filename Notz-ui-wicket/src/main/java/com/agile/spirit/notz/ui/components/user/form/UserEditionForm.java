package com.agile.spirit.notz.ui.components.user.form;

import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.User;

public class UserEditionForm extends UserForm {

  private static final long serialVersionUID = 2613710719568994484L;

  public UserEditionForm(String id, IModel<User> userModel) {
    super(id, userModel);
  }

  @Override
  protected String getTitleKey() {
    return "edition";
  }

}
