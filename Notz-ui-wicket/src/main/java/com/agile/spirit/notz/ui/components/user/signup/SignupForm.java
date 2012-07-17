package com.agile.spirit.notz.ui.components.user.signup;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
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

public class SignupForm extends NotzPanel {

  private static final long serialVersionUID = 4907116541209734919L;

  private static final Logger LOGGER = Logger.getLogger(SignupForm.class);

  /* Components */
  Form form;
  TextField<String> usernameInput;
  TextField<String> emailInput;
  PasswordTextField passwordInput;
  PasswordTextField confirmationInput;

  public SignupForm(String id) {
    super(id);
    buildForm();
  }

  private void buildForm() {
    form = new Form("form") {

      @Override
      public void onSubmit() {
        String username = usernameInput.getModelObject();
        String email = emailInput.getModelObject();
        String password = passwordInput.getModelObject();
        String confirmation = confirmationInput.getModelObject();

        User user = User.create(username, email, password);
        WebResource webResource = NotzApplication.getWebResource();
        ClientResponse response = webResource.path("users/").entity(user).accept(MediaType.APPLICATION_XML).put(ClientResponse.class);
        LOGGER.info("Response status = " + response.getClientResponseStatus());

        User returnedUser = response.getEntity(User.class);
        if (returnedUser != null) {
          getNotzSession().setUser(returnedUser);
          setResponsePage(NoteListPage.class);
        }
      }
    };
    add(form);

    buildUsernameInput();
    buildEmailInput();
    buildPasswordInput();
    buildConfirmationInput();
    buildRegistrationButton();
  }

  private void buildUsernameInput() {
    usernameInput = new RequiredTextField<String>("username", new Model<String>());
    usernameInput.setDefaultModel(new Model<String>("admin"));
    form.add(usernameInput);
  }

  private void buildEmailInput() {
    emailInput = new RequiredTextField<String>("email", new Model<String>());
    emailInput.setDefaultModel(new Model<String>("admin@agile-spirit.fr"));
    form.add(emailInput);
  }

  private void buildPasswordInput() {
    passwordInput = new PasswordTextField("password", new Model<String>());
    passwordInput.setResetPassword(false);
    passwordInput.setRequired(true);
    passwordInput.setDefaultModel(new Model<String>("admin"));
    form.add(passwordInput);
  }

  private void buildConfirmationInput() {
    confirmationInput = new PasswordTextField("confirmation", new Model<String>());
    confirmationInput.setResetPassword(false);
    confirmationInput.setRequired(true);
    confirmationInput.setDefaultModel(new Model<String>("admin"));
    form.add(confirmationInput);
  }

  private void buildRegistrationButton() {
    SubmitLink registrationButton = new SubmitLink("registrationButton", form) {
    };
    form.add(registrationButton);
  }

}
