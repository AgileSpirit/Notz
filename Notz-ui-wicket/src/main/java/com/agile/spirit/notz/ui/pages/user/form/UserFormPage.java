package com.agile.spirit.notz.ui.pages.user.form;


import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.UserServiceImpl;
import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.user.form.UserEditionForm;
import com.agile.spirit.notz.ui.components.user.form.UserForm;
import com.agile.spirit.notz.ui.components.user.form.UserRegistrationForm;

public class UserFormPage extends NotzPage {

  private static final long serialVersionUID = -4025204808046534279L;

  /* Components */
  UserForm userForm;

  /* Models */
  IModel<User> userModel;
  boolean edition;

  public UserFormPage() {
    super();
    buildUserRegistrationForm();
  }

  public UserFormPage(PageParameters params) {
    Integer userId = Integer.parseInt(params.get("user").toString());
    if (UserServiceImpl.getInstance().getUserById(userId) != null) {
      buildUserEditionForm(userId);
    } else {
      buildUserRegistrationForm();
    }
  }

  private void buildUserRegistrationForm() {
    userModel = new CompoundPropertyModel<User>(new LoadableDetachableModel<User>() {
      @Override
      protected User load() {
        User user = User.create();
        return user;
      }
    });
    edition = false;
    add(new UserRegistrationForm("userForm", userModel));
  }

  private void buildUserEditionForm(final Integer userId) {
    userModel = new CompoundPropertyModel<User>(new LoadableDetachableModel<User>() {
      @Override
      protected User load() {
        User user = UserServiceImpl.getInstance().getUserById(userId);
        return user;
      }
    });
    edition = true;
    add(new UserEditionForm("userForm", userModel));
  }

}
