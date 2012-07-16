package com.agile.spirit.notz.ui.components.user.login;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzApplication;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class LoginForm extends NotzPanel {

  private static final long serialVersionUID = 4907116541209734919L;

  /* Components */
  Form form;
  TextField<String> loginInput;
  PasswordTextField passwordInput;

  public LoginForm(String id) {
    super(id);
    buildForm();
  }

  private void buildForm() {
    form = new Form("form") {

      @Override
      public void onSubmit() {
        String login = loginInput.getModelObject();
        String password = passwordInput.getModelObject();

        WebResource webResource = NotzApplication.getWebResource();
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("login", login);
        params.add("password", password);
        ClientResponse response = webResource.path("users/login").post(ClientResponse.class, params);

        User user = response.getEntity(User.class);
        if (user != null) {
          getNotzSession().setUser(user);
          setResponsePage(NoteListPage.class);
        }
      }

    };
    add(form);

    buildLoginInput();
    buildPasswordInput();
    buildLoginButton();

  }

  private void buildLoginInput() {
    loginInput = new RequiredTextField<String>("login", new Model<String>());
    loginInput.setDefaultModelObject("admin@agile-spirit.fr");
    form.add(loginInput);
  }

  private void buildPasswordInput() {
    passwordInput = new PasswordTextField("password", new Model<String>());
    passwordInput.setRequired(true);
    passwordInput.setDefaultModelObject("admin");
    form.add(passwordInput);
  }

  private void buildLoginButton() {
    SubmitLink loginButton = new SubmitLink("loginButton", form);
    form.add(loginButton);
  }

}
