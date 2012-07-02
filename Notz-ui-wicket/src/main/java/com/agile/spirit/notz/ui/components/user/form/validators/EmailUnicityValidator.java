package com.agile.spirit.notz.ui.components.user.form.validators;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.StringValidator;

public class EmailUnicityValidator extends StringValidator {

  private static final long serialVersionUID = 1152671348894699550L;

  private static EmailUnicityValidator instance;

  private EmailUnicityValidator() {
    super();
  }

  public static EmailUnicityValidator getInstance() {
    if (instance == null) {
      instance = new EmailUnicityValidator();
    }
    return instance;
  }

  @Override
  protected void onValidate(IValidatable<String> validatable) {

    // FIXME : following might be a real implementation
    //
    //		String email = validatable.getValue();
    //		UserService userService = new UserServiceImpl();
    //		if (userService.findByEmail(email) != null) {
    //			error(validatable);
    //		}

    if ("not.unique@mail.com".equals(validatable.getValue())) {
      error(validatable);
    }
  }

}
