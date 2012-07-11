package com.agile.spirit.notz.ui.components.note.list;

import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzApplication;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class NoteDataProvider implements IDataProvider<Note> {

  private static final long serialVersionUID = 1L;

  private final Model<User> userModel;

  public NoteDataProvider(Model<User> userModel) {
    this.userModel = userModel;
  }

  @Override
  public void detach() {
    // TODO Auto-generated method stub
  }

  @Override
  public Iterator<? extends Note> iterator(int first, int count) {
    WebResource webResource = NotzApplication.getWebResource();
    MultivaluedMap<String, String> params = new MultivaluedMapImpl();
    params.add("first", "" + first);
    params.add("count", "" + count);
    List<Note> notes = webResource.path("notes/" + getUser().getId()).queryParams(params).get(new GenericType<List<Note>>() {
    });
    return notes.iterator();
  }

  @Override
  public int size() {
    WebResource webResource = NotzApplication.getWebResource();
    List<Note> notes = webResource.path("notes/" + getUser().getId()).get(new GenericType<List<Note>>() {
    });
    return notes.size();
  }

  @Override
  public IModel<Note> model(Note object) {
    int index = getUser().getNotes().indexOf(object);
    return new PropertyModel<Note>(userModel, "notes." + index);
  }

  /*
   * HELPER
   */

  public User getUser() {
    return userModel.getObject();
  }

}
