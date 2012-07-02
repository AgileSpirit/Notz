package com.agile.spirit.notz.ui.components.user.form.section;

import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.User;

public class PersonalDataSection extends UserFormSection {

  private static final long serialVersionUID = 1L;

  /* Components */
  RequiredTextField<String> firstNameInput;
  RequiredTextField<String> lastNameInput;

  public PersonalDataSection(String id, IModel<User> user) {
    super(id, "personalDataFeedback", user);

    buildFirstNameInput();
    buildLastNameInput();
  }

  private void buildFirstNameInput() {
    firstNameInput = new RequiredTextField<String>("firstName");
    add(firstNameInput);
  }

  private void buildLastNameInput() {
    lastNameInput = new RequiredTextField<String>("lastName");
    add(lastNameInput);
  }

  @Override
  public void resetFields() {
    firstNameInput.clearInput();
    lastNameInput.clearInput();
  }
  
}
