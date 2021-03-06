package com.agile.spirit.notz.ui.components.note.form;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ws.AbstractWebRequest;

public abstract class NoteForm extends NotzPanel {

  private static final long serialVersionUID = 4907116541209734919L;

  private static final Logger LOGGER = Logger.getLogger(NoteForm.class);

  /* Components */
  Form<Note> form;

  /* Model data */
  IModel<Note> model;

  public NoteForm(String id, Note note) {
    super(id);
    this.model = new CompoundPropertyModel<Note>(note);
  }

  public NoteForm(String id, IModel<Note> model) {
    super(id);
    this.model = new CompoundPropertyModel<Note>(model);
  }

  @Override
  protected void onInitialize() {
    super.onInitialize();
    buildForm();
  }

  private void buildForm() {
    form = new Form<Note>("form", model) {
      @Override
      public void onSubmit() {
        final Note note = model.getObject();
        final User user = getNotzSession().getUser();
        note.setUser(user);

        AbstractWebRequest request = getSubmitRequest(note);
        request.execute();
      }
    };
    form.setOutputMarkupId(true);

    buildTitleInput();
    buildDescriptionInput();
    buildButtonBar();

    add(form);
  }

  protected abstract AbstractWebRequest getSubmitRequest(final Note note);

  private void buildTitleInput() {
    RequiredTextField<String> titleInput = new RequiredTextField<String>("title");
    form.add(titleInput);
  }

  private void buildDescriptionInput() {
    TextArea<String> descriptionInput = new TextArea<String>("description");
    form.add(descriptionInput);
  }

  private void buildButtonBar() {
    buildCancelButton();
    buildValidateButton();
  }

  protected abstract String getValidateButtonKey();

  private void buildValidateButton() {
    final Note note = model.getObject();
    SubmitLink validateButton = new SubmitLink("validateButton") {
    };
    form.add(validateButton);
  }

  private void buildCancelButton() {
    AjaxSubmitLink cancelButton = new AjaxSubmitLink("cancelButton") {

      @Override
      protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
        closeModal(target);
      }

      @Override
      protected void onError(AjaxRequestTarget target, Form<?> form) {
        // Should never happens
      }
    };
    cancelButton.setDefaultFormProcessing(false);
    form.add(cancelButton);
  }

}
