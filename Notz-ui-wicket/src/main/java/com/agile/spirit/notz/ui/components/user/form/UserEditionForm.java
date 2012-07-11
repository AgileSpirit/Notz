package com.agile.spirit.notz.ui.components.user.form;

import org.apache.wicket.model.CompoundPropertyModel;

import com.agile.spirit.notz.domain.User;

public class UserEditionForm extends UserForm {

  private static final long serialVersionUID = 2613710719568994484L;

  public UserEditionForm(String id) {
    super(id);
  }

  @Override
  protected void buildModel() {
    this.model = new CompoundPropertyModel<User>(getNotzSession().getUser());
  }

  @Override
  protected String getTitleKey() {
    return "edition";
  }

  @Override
  protected String getValidateButtonKey() {
    return "update";
  }

  @Override
  protected boolean isCreationMode() {
    return false;
  }

  @Override
  protected boolean isGtuSectionVisible() {
    return false;
  }

}
