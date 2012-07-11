package com.agile.spirit.notz.ui.pages.note.list;

import com.agile.spirit.notz.ui.NotzPage;
import com.agile.spirit.notz.ui.components.note.list.NoteList;

public class NoteListPage extends NotzPage {

  private static final long serialVersionUID = 1L;

  public NoteListPage() {
    super();
    buildNoteList();
  }

  private void buildNoteList() {
    NoteList noteList = new NoteList("noteList");
    add(noteList);
  }

}
