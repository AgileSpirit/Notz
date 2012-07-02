package com.agile.spirit.notz.ui.pages.note.list;

import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.note.list.NoteList;

public class NoteListPage extends NotzPage {

  private static final long serialVersionUID = 1L;

  NoteList noteList;

  public NoteListPage() {
    super();
  }

  @Override
  public void onInitialize() {
    super.onInitialize();
    buildNoteList();
  }
  
  private void buildNoteList() {
    noteList = new NoteList("noteList");
    add(noteList);
  }

}
