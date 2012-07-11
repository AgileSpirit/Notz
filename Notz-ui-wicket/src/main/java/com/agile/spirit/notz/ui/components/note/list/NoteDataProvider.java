package com.agile.spirit.notz.ui.components.note.list;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;

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
    List<Note> notes = getUser().getNotes();
    return notes.subList(first, first + count).iterator();
  }

  // @Override
  // public Iterator<? extends Note> iterator(int first, int count) {
  // WebResource webResource = NotzApplication.getWebResource();
  // ClientResponse response = webResource.path("notes/" + getUser().getId()).get(ClientResponse.class);
  // List objects = response.getEntity(List.class);
  // List<Note> notes = new ArrayList<Note>();
  // if (objects != null) {
  // for (Object object : objects) {
  // if (object instanceof Note) {
  // notes.add((Note) object);
  // }
  // }
  // }
  // return notes.subList(first, first + count).iterator();
  // }
  //
  @Override
  public int size() {
    List<Note> notes = getUser().getNotes();
    return notes.size();
  }

  // @Override
  // public int size() {
  // WebResource webResource = NotzApplication.getWebResource();
  // ClientResponse response = webResource.path("notes/" + getUser().getId()).get(ClientResponse.class);
  // List objects = response.getEntity(List.class);
  // List<Note> notes = new ArrayList<Note>();
  // if (objects != null) {
  // for (Object object : objects) {
  // if (object instanceof Note) {
  // notes.add((Note) object);
  // }
  // }
  // }
  // return notes.size();
  // }
  //
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
