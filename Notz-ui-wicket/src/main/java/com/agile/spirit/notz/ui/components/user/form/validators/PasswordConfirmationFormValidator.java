package com.agile.spirit.notz.ui.components.user.form.validators;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.validation.IFormValidator;

public class PasswordConfirmationFormValidator implements IFormValidator {

  private static final long serialVersionUID = 1L;

  final PasswordConfirmationFieldsGroup group;

  public PasswordConfirmationFormValidator(final PasswordConfirmationFieldsGroup group) {
    this.group = group;
  }

  @Override
  public FormComponent<?>[] getDependentFormComponents() {
    return new FormComponent<?>[]{ group.getPasswordInput(), group.getConfirmationInput() };
  }

  @Override
  public void validate(Form<?> form) {
    String passwordData = group.getPasswordInput().getInput();
    String confirmationData = group.getConfirmationInput().getInput();

    if (!passwordData.equals(confirmationData)) {
      group.getConfirmationInput().error(form.getString("error.passwordConfirmation"));
    }
  }

}
