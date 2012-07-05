package com.agile.spirit.notz.ui.components.user.login;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.UserServiceImpl;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;

public class LoginForm extends NotzPanel {

  private static final long serialVersionUID = 4907116541209734919L;

  /* Components */
  Form form;
  TextField<String> emailInput;
  PasswordTextField passwordInput;

  public LoginForm(String id) {
    super(id);
    buildForm();
  }

  private void buildForm() {
    form = new Form("form") {
      
      @Override
      public void onSubmit() {
        String email = emailInput.getModelObject();
        String password = passwordInput.getModelObject();
        User user = UserServiceImpl.getInstance().loginUser(email, password);
        if (user != null) {
          getWicketSession().setUser(user);
          setResponsePage(NoteListPage.class);
        }
      }
    };
    add(form);

    buildEmailInput();
    buildPasswordInput();
    buildLoginButton();
  }
  
  private void buildEmailInput() {
    emailInput = new RequiredTextField<String>("email", new Model<String>());
    emailInput.setDefaultModelObject("admin@agile-spirit.fr");
    form.add(emailInput);
  }

  private void buildPasswordInput() {
    passwordInput = new PasswordTextField("password", new Model<String>());
    passwordInput.setResetPassword(false);
    passwordInput.setRequired(true);
    passwordInput.setDefaultModelObject("admin");
    form.add(passwordInput);
  }

  private void buildLoginButton() {
    Button loginButton = new Button("loginButton");
    form.add(loginButton);
  }

}
