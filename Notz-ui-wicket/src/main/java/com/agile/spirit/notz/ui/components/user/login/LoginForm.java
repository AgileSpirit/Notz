package com.agile.spirit.notz.ui.components.user.login;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.agile.spirit.notz.ui.ws.PostRequest;
import com.agile.spirit.notz.ui.ws.WebResourceRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
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
        final String login = loginInput.getModelObject();
        final String password = passwordInput.getModelObject();

        WebResourceRequest request = new PostRequest() {
          
          @Override
          public void onSuccess(ClientResponse response) {
            User user = response.getEntity(User.class);
            if (user != null) {
              getNotzSession().setUser(user);
              setResponsePage(NoteListPage.class);
            }
          }
          
          @Override
          public void onError(ClientResponse response) {
            // TODO Auto-generated method stub
            
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
        ClientResponse response = request.execute();
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
