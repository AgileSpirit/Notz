package com.agile.spirit.notz.ui.components.note.form;

import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.Note;

public class NoteCreationForm extends NoteForm {

  private static final long serialVersionUID = 1L;

  public NoteCreationForm(String id, IModel<Note> note) {
    super(id, note);
  }

  @Override
  protected String getValidateButtonKey() {
    return "add";
  }

  @Override
  protected boolean isCreationMode() {
    return true;
  }

}
