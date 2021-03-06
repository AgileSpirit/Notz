package com.agile.spirit.notz.ui.pages.note.list;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.note.form.NoteCreationForm;
import com.agile.spirit.notz.ui.components.note.list.NoteList;

public class NoteListPage extends NotzPage {

  private static final long serialVersionUID = 1L;

  /* Components */
  private WebMarkupContainer toolBar;

  public NoteListPage() {
    super();
    buildToolBar();
    buildNoteList();
  }

  private void buildToolBar() {
    toolBar = new WebMarkupContainer("toolBar");
    add(toolBar);

    buildAddNoteButton();
    buildExportNotesButton();
    buildSearchField();
  }

  private void buildAddNoteButton() {
    toolBar.add(new AjaxLink("addNoteButton") {
      @Override
      public void onClick(AjaxRequestTarget target) {
        ModalWindow modal = getModal();
        modal.setTitle(getString("noteCreation"));
        modal.setContent(new NoteCreationForm(modal.getContentId(), new Model<Note>(Note.create())));
        showModal(target);
      }
    });

  }

  private void buildExportNotesButton() {
    toolBar.add(new AjaxLink("exportNotesButton") {
      @Override
      public void onClick(AjaxRequestTarget target) {
        // TODO
      }
    });

  }

  private void buildSearchField() {
    buildSearchNotesInput();
    buildSearchNotesButton();
  }

  private void buildSearchNotesInput() {
    toolBar.add(new TextField<String>("searchNotesInput"));
  }

  private void buildSearchNotesButton() {
    toolBar.add(new AjaxLink("searchNotesButton") {
      @Override
      public void onClick(AjaxRequestTarget target) {
        // TODO
      }
    });

  }

  private void buildNoteList() {
    NoteList noteList = new NoteList("noteList");
    add(noteList);
  }

}
