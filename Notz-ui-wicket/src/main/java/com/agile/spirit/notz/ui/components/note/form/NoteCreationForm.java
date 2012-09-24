package com.agile.spirit.notz.ui.components.note.form;

import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.ui.pages.note.list.NoteListPage;
import com.agile.spirit.notz.ws.AbstractWebRequest;
import com.agile.spirit.notz.ws.PostRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class NoteCreationForm extends NoteForm {

  private static final long serialVersionUID = 1L;

  public NoteCreationForm(String id, Note note) {
    super(id, note);
  }

  public NoteCreationForm(String id, IModel<Note> note) {
    super(id, note);
  }

  @Override
  protected String getValidateButtonKey() {
    return "add";
  }

  @Override
  protected AbstractWebRequest getSubmitRequest(final Note note) {
    AbstractWebRequest request = new PostRequest() {

      @Override
      public WebResource.Builder configureWebResource(WebResource webResource) {
        return webResource.path("notes/").entity(note);
      }

      @Override
      public void onSuccess(ClientResponse response) {
        setResponsePage(NoteListPage.class);
      }

      @Override
      public void onError(ClientResponse response) {
        // TODO Auto-generated method stub
      }

    };
    return request;
  }

}
