package com.agile.spirit.notz.ui.components.user.form;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.UserServiceImpl;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.components.user.form.section.GtuSection;
import com.agile.spirit.notz.ui.components.user.form.section.IdentificationSection;
import com.agile.spirit.notz.ui.components.user.form.section.PersonalDataSection;
import com.agile.spirit.notz.ui.components.user.form.validators.PasswordConfirmationFormValidator;
import com.agile.spirit.notz.ui.pages.user.login.LoginPage;

public abstract class UserForm extends NotzPanel {

  private static final long serialVersionUID = 1L;

  /* Components */
  Form<User> form;
  PersonalDataSection personalDataSection;
  IdentificationSection identificationSection;
  GtuSection gtuSection;

  /* Model data */
  IModel<User> model;

  public UserForm(String id) {
    super(id);
    buildModel();
    buildForm();
  }

  protected abstract void buildModel();

  protected abstract String getTitleKey();

  private void buildForm() {
    form = new Form<User>("form");
    form.setOutputMarkupId(true);

    buildTitle();
    buildPersonalDataSection();
    buildIdentificationSection();
    buildGtuSection();
    buildButtonBar();

    add(form);
  }

  private void buildTitle() {
    Label title = new Label("title", new StringResourceModel(getTitleKey(), UserForm.this, null));
    form.add(title);
  }

  private void buildPersonalDataSection() {
    personalDataSection = new PersonalDataSection("personalDataSection", model);
    form.add(personalDataSection);
  }

  protected abstract boolean isCreationMode();

  private void buildIdentificationSection() {
    identificationSection = new IdentificationSection("identificationSection", model, isCreationMode());
    form.add(identificationSection);
    form.add(new PasswordConfirmationFormValidator(identificationSection));
  }

  protected abstract boolean isGtuSectionVisible();

  private void buildGtuSection() {
    gtuSection = new GtuSection("gtuSection", model);
    gtuSection.setOutputMarkupId(true);
    gtuSection.setOutputMarkupPlaceholderTag(true);
    gtuSection.setVisibilityAllowed(isGtuSectionVisible());
    form.add(gtuSection);
  }

  private void buildButtonBar() {
    buildResetButton();
    buildValidateButton();
  }

  private void buildResetButton() {
    Button resetButton = new Button("resetButton");
    resetButton.add(new AjaxEventBehavior("onClick") {
      private static final long serialVersionUID = 1L;

      @Override
      protected void onEvent(AjaxRequestTarget target) {
        personalDataSection.resetFields();
        identificationSection.resetFields();
        target.add(form);
      }

    });
    form.add(resetButton);
  }

  protected abstract String getValidateButtonKey();

  private void buildValidateButton() {
    AjaxButton validateButton = new AjaxButton("validateButton") {
      private static final long serialVersionUID = 1L;

      @Override
      protected void onError(AjaxRequestTarget target, Form<?> form) {
        target.add(form);
        // super.onError(target, form);
      }

      @Override
      protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

        UserServiceImpl.getInstance().saveOrUpdate(model.getObject());
        setResponsePage(LoginPage.class);
      }

    };
    validateButton.setModel(new StringResourceModel(getValidateButtonKey(), UserForm.this, null));
    form.add(validateButton);
  }

}
