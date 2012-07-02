package com.agile.spirit.notz.ui.components.user.form.section;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.components.user.form.validators.EmailUnicityValidator;
import com.agile.spirit.notz.ui.components.user.form.validators.PasswordConfirmationFieldsGroup;

public class IdentificationSection extends UserFormSection implements PasswordConfirmationFieldsGroup {

  private static final long serialVersionUID = 1L;

  /* Components */
  RequiredTextField<String> emailInput;
  PasswordTextField passwordInput;
  PasswordTextField confirmationInput;

  /* Attributes */
  boolean isCreationMode;

  public IdentificationSection(String id, IModel<User> model, boolean isCreationMode) {
    super(id, "identificationFeedback", model);
    this.isCreationMode = isCreationMode;

    buildEmailInput();
    buildPasswordInput();
    buildConfirmationInput();
  }

  private void buildEmailInput() {
    emailInput = new RequiredTextField<String>("email");
    emailInput.add(EmailAddressValidator.getInstance());
    emailInput.add(EmailUnicityValidator.getInstance());
    add(emailInput);
  }

  private void buildPasswordInput() {
    passwordInput = new PasswordTextField("password");
    passwordInput.setResetPassword(false);
    passwordInput.setRequired(true);
    add(passwordInput);
  }

  private void buildConfirmationInput() {
    confirmationInput = new PasswordTextField("confirmation", new Model<String>());
    confirmationInput.setResetPassword(false);
    confirmationInput.setRequired(true);
    if (isCreationMode) {
      confirmationInput.setVisible(true);
    } else {
      confirmationInput.setVisible(false);
    }
    add(confirmationInput);
  }

  @Override
  public void resetFields() {
    emailInput.clearInput();
    passwordInput.clearInput();
    confirmationInput.clearInput();
  }

  @Override
  public TextField<String> getPasswordInput() {
    return this.passwordInput;
  }

  @Override
  public TextField<String> getConfirmationInput() {
    return this.confirmationInput;
  }

}
