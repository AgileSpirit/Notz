package com.agile.spirit.notz.ui.components.note.list;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.agile.spirit.notz.domain.Note;
import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.NoteServiceImpl;

public class NoteDataProvider implements IDataProvider<Note> {

  private static final long serialVersionUID = 1L;

  private final IModel<User> userModel;

  public NoteDataProvider(IModel<User> userModel) {
    this.userModel = userModel;
  }
  
  @Override
  public void detach() {
    // TODO Auto-generated method stub

  }

  @Override
  public Iterator<? extends Note> iterator(int first, int count) {
    return NoteServiceImpl.getInstance().findNotesByUser(getUser(), first, count).iterator();
  }

  @Override
  public int size() {
    return getUser().getNotes().size();
  }

  @Override
  public IModel<Note> model(Note object) {
    int index = getUser().getNotes().indexOf(object);
    return new PropertyModel<Note>(userModel, "notes." + index);
  }

  /*
   * HELPER
   */
  private User getUser() {
    User user = (User) userModel.getObject();
    return user;
  }

}
