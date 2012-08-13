package com.agile.spirit.notz.ui.pages.user.settings;



import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.user.form.UserEditionForm;

public class UserSettingsPage extends NotzPage {

  private static final long serialVersionUID = 1L;

  public UserSettingsPage() {
    super();
    buildUserForm();
  }

  private void buildUserForm() {
    add(new UserEditionForm("userForm", new Model<User>(getNotzSession().getUser())));
  }

}
