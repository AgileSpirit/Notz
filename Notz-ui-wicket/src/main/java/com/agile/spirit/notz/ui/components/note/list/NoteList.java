package com.agile.spirit.notz.ui.components.note.list;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzApplication;
import com.agile.spirit.notz.ui.NotzPanel;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class NoteList extends NotzPanel {

  private static final long serialVersionUID = 1L;

  /* Model */
  List<Note> notes;

  /* Components */
  ListView<Note> listView;

  public NoteList(String id) {
    super(id);
  }

  @Override
  public void onInitialize() {
    super.onInitialize();
    loadUserNotes();
    buildListView();
  }

  private void loadUserNotes() {
    User user = getNotzSession().getUser();
    WebResource webResource = NotzApplication.getWebResource();
    this.notes = webResource.path("notes/" + user.getId()).get(new GenericType<List<Note>>() {
    });
    if (notes == null) {
      System.out.println("No notes retrieved (notes == null)");
    } else {
      System.out.println(notes.size() + " notes retrieved");
    }
  }

  private void buildListView() {
    listView = new ListView<Note>("note", notes) {

      @Override
      protected void populateItem(ListItem<Note> item) {
        Note note = item.getModelObject();

        String shortedTitle = shortCutString(note.getTitle(), 26);
        Label title = new Label("title", shortedTitle);
        item.add(title);

        Label description = new Label("description", note.getDescription());
        item.add(description);

        item.add(new AjaxLink("edit") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            // TODO
          }
        });

        item.add(new AjaxLink("delete") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            // TODO
          }
        });

        String displayedDate = "";
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        if (note.getModificationDate() != null) {
          displayedDate += df.format(note.getModificationDate());
        } else {
          displayedDate += df.format(note.getCreationDate());
        }
        Label modificationDate = new Label("modificationDate", displayedDate);
        item.add(modificationDate);

        item.add(new AjaxLink("pdfLink") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            // TODO
          }
        });

        item.add(new AjaxLink("facebookLink") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            // TODO
          }
        });

        item.add(new AjaxLink("twitterLink") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            // TODO
          }
        });

        item.add(new AjaxLink("emailLink") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            // TODO
          }
        });

        item.add(new AjaxLink("permaLink") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            // TODO
          }
        });

      }
    };
    add(listView);
  }

  private String shortCutString(String phrase, int length) {
    if (phrase == null)
      return null;
    if (phrase.length() <= length)
      return phrase;
    return phrase.substring(0, length - 3) + "...";
  }

  // private void buildList() {
  // list = new DataView<Note>("list", new NoteDataProvider(new Model<User>(getNotzSession().getUser()))) {
  //
  // @Override
  // protected void populateItem(final Item<Note> item) {
  // final Note note = item.getModelObject();
  // String displayedTitle = shortCutString(note.getTitle(), 40);
  // item.add(new Label("title", displayedTitle));
  // int descriptionExtendedSize = (note.getTitle().length() <= 40) ? 40 - note.getTitle().length() : 0;
  // item.add(new Label("description", shortCutString(note.getDescription(), 40 + descriptionExtendedSize)));
  //
  // final PageParameters params = new PageParameters();
  // params.add("note", note.getId());
  // // item.add(new BookmarkablePageLink<NoteFormPage>("edit", NoteFormPage.class, params));
  // item.add(new AjaxLink("edit") {
  // @Override
  // public void onClick(AjaxRequestTarget target) {
  // IModel<Note> noteModel = new CompoundPropertyModel<Note>(new LoadableDetachableModel<Note>() {
  // @Override
  // protected Note load() {
  // return note;
  // }
  // });
  // NoteEditionForm noteRegistrationForm = new NoteEditionForm(getModal().getContentId(), noteModel);
  // configureModal(noteRegistrationForm, 400);
  // showModal(target);
  // }
  //
  // });
  // item.add(new Link<Integer>("delete", new Model<Integer>(note.getId())) {
  // @Override
  // public void onClick() {
  // NoteServiceImpl.getInstance().delete(getModelObject());
  // }
  // });
  // }
  // };
  // add(list);
  // list.setItemsPerPage(2);
  // buildListTitle();
  // }
  //
  //
  // private void buildListFooter() {
  // add(new PagingNavigator("navigator", list));
  // // buildAddNoteLink();
  // }
  //
  // private void buildAddNoteLink() {
  // AjaxLink addNoteLink = new AjaxLink("addNoteLink") {
  // @Override
  // public void onClick(AjaxRequestTarget ajaxRequestTarget) {
  // IModel<Note> noteModel = new CompoundPropertyModel<Note>(new LoadableDetachableModel<Note>() {
  // @Override
  // protected Note load() {
  // Note note = Note.create();
  // return note;
  // }
  // });
  // NoteCreationForm noteCreationForm = new NoteCreationForm(getModal().getContentId(), noteModel);
  // configureModal(noteCreationForm, 400);
  // showModal(ajaxRequestTarget);
  // }
  // };
  // add(addNoteLink);
  // }

}
