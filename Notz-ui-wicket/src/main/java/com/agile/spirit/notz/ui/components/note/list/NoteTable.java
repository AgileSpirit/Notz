package com.agile.spirit.notz.ui.components.note.list;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.NoteServiceImpl;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.components.note.form.NoteCreationForm;
import com.agile.spirit.notz.ui.components.note.form.NoteEditionForm;

public class NoteTable extends NotzPanel {

  private static final long serialVersionUID = 1L;

  /* Components */
  DataView<Note> list;

  public NoteTable(String id) {
    super(id);
  }

  @Override
  public void onInitialize() {
    super.onInitialize();
    buildList();
    buildListFooter();
  }

  private void buildListTitle() {
    Label listTitle = new Label("listTitle", list.getDataProvider().size() + " notes");
    add(listTitle);
  }

  private void buildList() {
    list = new DataView<Note>("list", new NoteDataProvider(new Model<User>(getNotzSession().getUser()))) {

      @Override
      protected void populateItem(final Item<Note> item) {
        final Note note = item.getModelObject();
        String displayedTitle = shortCutString(note.getTitle(), 40);
        item.add(new Label("title", displayedTitle));
        int descriptionExtendedSize = (note.getTitle().length() <= 40) ? 40 - note.getTitle().length() : 0;
        item.add(new Label("description", shortCutString(note.getDescription(), 40 + descriptionExtendedSize)));

        final PageParameters params = new PageParameters();
        params.add("note", note.getId());
        // item.add(new BookmarkablePageLink<NoteFormPage>("edit", NoteFormPage.class, params));
        item.add(new AjaxLink("edit") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            IModel<Note> noteModel = new CompoundPropertyModel<Note>(new LoadableDetachableModel<Note>() {
              @Override
              protected Note load() {
                return note;
              }
            });
            NoteEditionForm noteRegistrationForm = new NoteEditionForm(getModal().getContentId(), noteModel);
            configureModal(noteRegistrationForm, 400);
            showModal(target);
          }

        });
        item.add(new Link<Integer>("delete", new Model<Integer>(note.getId())) {
          @Override
          public void onClick() {
            NoteServiceImpl.getInstance().delete(getModelObject());
          }
        });
      }
    };
    add(list);
    list.setItemsPerPage(2);
    buildListTitle();
  }

  private String shortCutString(String phrase, int length) {
    if (phrase == null)
      return null;
    if (phrase.length() <= length)
      return phrase;
    return phrase.substring(0, length - 3) + "...";
  }

  private void buildListFooter() {
    add(new PagingNavigator("navigator", list));
    // buildAddNoteLink();
  }

  private void buildAddNoteLink() {
    AjaxLink addNoteLink = new AjaxLink("addNoteLink") {
      @Override
      public void onClick(AjaxRequestTarget ajaxRequestTarget) {
        IModel<Note> noteModel = new CompoundPropertyModel<Note>(new LoadableDetachableModel<Note>() {
          @Override
          protected Note load() {
            Note note = Note.create();
            return note;
          }
        });
        NoteCreationForm noteCreationForm = new NoteCreationForm(getModal().getContentId(), noteModel);
        configureModal(noteCreationForm, 400);
        showModal(ajaxRequestTarget);
      }
    };
    add(addNoteLink);
  }

}
