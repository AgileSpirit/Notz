package com.agile.spirit.notz.ui.components.user.form;

import javax.ws.rs.core.MediaType;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.agile.spirit.notz.ws.AbstractWebRequest;
import com.agile.spirit.notz.ws.PutRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public abstract class UserForm extends NotzPanel {

  private static final long serialVersionUID = 1L;

  /* Components */
  Form form;
  Label titleLabel;
  TextField<String> completeNameInput;
  RequiredTextField<String> usernameInput;
  RequiredTextField<String> emailInput;
  PasswordTextField passwordInput;
  PasswordTextField confirmationInput;

  // /* Models */
  // IModel<User> userModel;

  public UserForm(String id, IModel<User> userModel) {
    super(id);
    setDefaultModel(new CompoundPropertyModel<User>(userModel));
    buildForm();
  }

  private void buildForm() {
    form = new Form("form") {

      @Override
      public void onSubmit() {
        // String username = usernameInput.getModelObject();
        // String email = emailInput.getModelObject();
        // String password = passwordInput.getModelObject();
        // String completeName = completeNameInput.getModelObject();
        // String confirmation = confirmationInput.getModelObject();
        //
        final User user = getUser();

        AbstractWebRequest request = new PutRequest() {

          @Override
          public void onSuccess(ClientResponse response) {
            User returnedUser = response.getEntity(User.class);
            if (returnedUser != null) {
              getNotzSession().setUser(returnedUser);
              setResponsePage(NoteListPage.class);
            }
          }

          @Override
          public Builder configureWebResource(WebResource webResource) {
            return webResource.path("users/").entity(user).accept(MediaType.APPLICATION_XML);
          }
        };
        request.execute();
      }
    };
    add(form);

    buildTitleLabel();
    buildCompleteNameInput();
    buildUsernameInput();
    buildEmailInput();
    buildPasswordInput();
    buildConfirmationInput();
    buildValidateButton();
  }

  protected abstract String getTitleKey();

  private void buildTitleLabel() {
    titleLabel = new Label("title", getTitleKey());
    form.add(titleLabel);
  }

  private void buildCompleteNameInput() {
    completeNameInput = new TextField<String>("completeName");
    form.add(completeNameInput);
  }

  private void buildUsernameInput() {
    usernameInput = new RequiredTextField<String>("username");
    form.add(usernameInput);
  }

  private void buildEmailInput() {
    emailInput = new RequiredTextField<String>("email");
    form.add(emailInput);
  }

  private void buildPasswordInput() {
    passwordInput = new PasswordTextField("password");
    passwordInput.setResetPassword(false);
    passwordInput.setRequired(true);
    form.add(passwordInput);
  }

  private void buildConfirmationInput() {
    confirmationInput = new PasswordTextField("confirmation", new Model<String>());
    confirmationInput.setResetPassword(false);
    confirmationInput.setRequired(true);
    form.add(confirmationInput);
  }

  private void buildValidateButton() {
    SubmitLink validateButton = new SubmitLink("validateButton", form) {
    };
    form.add(validateButton);
  }

  private User getUser() {
    return (User) getDefaultModelObject();
  }

}
