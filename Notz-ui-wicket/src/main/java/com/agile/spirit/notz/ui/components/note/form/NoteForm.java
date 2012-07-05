package com.agile.spirit.notz.ui.components.note.form;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.StringResourceModel;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.services.NoteServiceImpl;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;

public abstract class NoteForm extends Panel {

  private static final long serialVersionUID = 4907116541209734919L;

  /* Components */
  Form<Note> form;

  /* Model data*/
  IModel<Note> model;

  public NoteForm(String id, IModel<Note> model) {
    super(id);
    this.model = model;
  }

  @Override
  protected void onInitialize() {
    super.onInitialize();
    buildForm();
  }

  protected abstract String getTitleKey();

  private void buildForm() {
    form = new Form<Note>("form");
    form.setOutputMarkupId(true);

    buildFormTitle();
    buildTitleInput();
    buildDescriptionInput();
    buildButtonBar();

    add(form);
  }

  private void buildFormTitle() {
    Label formTitle = new Label("formTitle", new StringResourceModel(getTitleKey(), NoteForm.this, null));
    form.add(formTitle);
  }


  private void buildTitleInput() {
    RequiredTextField<String> titleInput = new RequiredTextField<String>("title");
    form.add(titleInput);
  }

  private void buildDescriptionInput() {
    TextArea<String> descriptionInput = new TextArea<String>("description");
    form.add(descriptionInput);
  }

  protected abstract boolean isCreationMode();

  private void buildButtonBar() {
    buildCancelButton();
    buildValidateButton();
  }

  protected abstract String getValidateButtonKey();

  private void buildValidateButton() {
    Button validateButton = new Button("validateButton") {
      private static final long serialVersionUID = 1L;

      @Override
      public void onSubmit() {
        NoteServiceImpl.getInstance().saveOrUpdate(model.getObject());
        setResponsePage(NoteListPage.class);
      }

    };
    validateButton.setModel(new StringResourceModel(getValidateButtonKey(), NoteForm.this, null));
    form.add(validateButton);
  }

  private void buildCancelButton() {
    Button cancelButton = new Button("cancelButton") {
      private static final long serialVersionUID = 1L;

      @Override
      public void onSubmit() {
        
      }
    };
    cancelButton.setDefaultFormProcessing(false);
    form.add(cancelButton);
  }

}
