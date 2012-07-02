package com.agile.spirit.notz.ui.components.note.form;

import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.Note;

public class NoteEditionForm extends NoteForm {

  private static final long serialVersionUID = 1L;

  public NoteEditionForm(String id, IModel<Note> note) {
    super(id, note);
  }

  @Override
  protected String getTitleKey() {
    return "edition";
  }

  @Override
  protected String getValidateButtonKey() {
    return "update";
  }

  @Override
  protected boolean isCreationMode() {
    return false;
  }

}
