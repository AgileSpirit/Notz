package com.agile.spirit.notz.ui.components.user.login;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.agile.spirit.notz.ws.AbstractWebRequest;
import com.agile.spirit.notz.ws.PostRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class LoginForm extends NotzPanel {

  private static final long serialVersionUID = 4907116541209734919L;

  private static final Logger LOGGER = Logger.getLogger(LoginForm.class);
  
  /* Components */
  Form form;
  RequiredTextField<String> loginInput;
  PasswordTextField passwordInput;
  SubmitLink loginButton;
  CheckBox rememberMeCB;

  public LoginForm(String id) {
    super(id);
    LOGGER.debug("in constructor");
    buildForm();
  }

  private void buildForm() {
    form = new Form("form") {

      @Override
      public void onSubmit() {
        final String login = loginInput.getModelObject();
        final String password = passwordInput.getModelObject();

        AbstractWebRequest request = new PostRequest() {
          
          @Override
          public void onSuccess(ClientResponse response) {
            User user = response.getEntity(User.class);
            if (user != null) {
              getNotzSession().setUser(user);
              setResponsePage(NoteListPage.class);
            }
          }
          
          @Override
          public Builder configureWebResource(WebResource webResource) {
            return webResource.path("users/login").accept(MediaType.APPLICATION_XML);
          }
        };
        
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("login", login);
        params.add("password", password);
        request.setParams(params);
        request.execute();
      }

    };
    add(form);

    form.add(buildLoginInput());
    form.add(buildPasswordInput());
    form.add(buildLoginButton());
    form.add(buildRememberMeCB());
  }

  private RequiredTextField<String> buildLoginInput() {
    loginInput = new RequiredTextField<String>("login", new Model<String>());
    loginInput.setDefaultModelObject("admin@agile-spirit.fr");
    return loginInput;
  }

  private PasswordTextField buildPasswordInput() {
    passwordInput = new PasswordTextField("password", new Model<String>());
    passwordInput.setRequired(true);
    passwordInput.setDefaultModelObject("admin");
    return passwordInput;
  }

  private SubmitLink buildLoginButton() {
    loginButton = new SubmitLink("loginButton", form);
    return loginButton;
  }

  private CheckBox buildRememberMeCB() {
    rememberMeCB = new CheckBox("rememberMeCB", new Model<Boolean>(new Boolean(true)));
    return rememberMeCB;
  }
  
}
