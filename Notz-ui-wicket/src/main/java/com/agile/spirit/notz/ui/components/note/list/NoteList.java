package com.agile.spirit.notz.ui.components.note.list;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.components.note.form.NoteEditionForm;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.agile.spirit.notz.ws.AbstractWebRequest;
import com.agile.spirit.notz.ws.DeleteRequest;
import com.agile.spirit.notz.ws.GetRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

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
    final User user = getNotzSession().getUser();
    AbstractWebRequest request = new GetRequest() {

      @Override
      public void onSuccess(ClientResponse response) {
        notes = response.getEntity(new GenericType<List<Note>>() {
        });
        if (notes == null) {
          System.out.println("No notes retrieved (notes == null)");
        }
        else {
          System.out.println(notes.size() + " notes retrieved");
        }
      }

      @Override
      public void onError(ClientResponse response) {
        // TODO Auto-generated method stub

      }

      @Override
      public Builder configureWebResource(WebResource webResource) {
        return webResource.path("notes/" + user.getId()).getRequestBuilder();
      }
    };
    request.execute();
  }

  private void buildListView() {
    listView = new ListView<Note>("note", notes) {

      @Override
      protected void populateItem(ListItem<Note> item) {
        final Note note = item.getModelObject();

        String shortedTitle = shortCutString(note.getTitle(), 26);
        Label title = new Label("title", shortedTitle);
        item.add(title);

        Label description = new Label("description", note.getDescription());
        description.setEscapeModelStrings(true);
        item.add(description);

        item.add(new AjaxLink("edit") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            ModalWindow modal = getModal();
            modal.setTitle(getString("noteEdition"));
            modal.setContent(new NoteEditionForm(modal.getContentId(), note));
            showModal(target);
          }
        });

        item.add(new AjaxLink("delete") {
          @Override
          public void onClick(AjaxRequestTarget target) {
            AbstractWebRequest request = new DeleteRequest() {

              @Override
              public void onSuccess(ClientResponse response) {
                setResponsePage(NoteListPage.class);
              }

              @Override
              public void onError(ClientResponse response) {
                // DO NOTHING
                System.err.println("ERROR");
              }

              @Override
              public Builder configureWebResource(WebResource webResource) {
                return webResource.path("notes/" + note.getId()).getRequestBuilder();
              }
            };
            request.execute();
          }
        });

        String displayedDate = "";
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        if (note.getModificationDate() != null) {
          displayedDate += df.format(note.getModificationDate());
        }
        else {
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

}
