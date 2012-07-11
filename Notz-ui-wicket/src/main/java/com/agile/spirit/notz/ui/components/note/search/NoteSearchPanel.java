package com.agile.spirit.notz.ui.components.note.search;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.services.SearchCriteria;
import com.agile.spirit.notz.ui.NotzPanel;

public class NoteSearchPanel extends NotzPanel {

  private static final long serialVersionUID = 4907116541209734919L;

  /* Components */
  Form<User> form;
  TextField<String> patternInput;

  /* Model data */
  IModel<SearchCriteria> model;

  public NoteSearchPanel(String id) {
    super(id);
    buildForm();
  }

  private void buildForm() {
    /*
     * TEMPORARY EMPTY METHOD
     */
  }

  // @Override
  // public void onConfigure() {
  // super.onConfigure();
  // this.model = new Model<SearchCriteria>(getNotzSession().getUserSearchCriteria());
  // this.setDefaultModel(new CompoundPropertyModel<SearchCriteria>(model));
  // }
  //
  // private void buildForm() {
  // form = new Form<User>("form");
  // form.setOutputMarkupId(true);
  //
  // buildPatternInput();
  // buildButtonBar();
  //
  // add(form);
  // }
  //
  // private void buildPatternInput() {
  // patternInput = new TextField<String>("pattern");
  // form.add(patternInput);
  // }
  //
  // private void buildButtonBar() {
  // buildResetButton();
  // buildSearchButton();
  // }
  //
  // private void buildResetButton() {
  // Button resetButton = new AjaxButton("resetButton") {
  // private static final long serialVersionUID = -6212470738648231613L;
  //
  // @Override
  // protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
  // getNotzSession().setUserSearchCriteria(new SearchCriteria());
  // setResponsePage(getPage());
  // }
  //
  // @Override protected void onError(AjaxRequestTarget target, Form<?> form) { }
  //
  // }.setDefaultFormProcessing(false);
  // form.add(resetButton);
  // }
  //
  // private void buildSearchButton() {
  // Button searchButton = new Button("searchButton") {
  // private static final long serialVersionUID = 342974993599665914L;
  //
  // @Override
  // public void onSubmit() {
  // setResponsePage(getPage());
  // }
  // };
  // form.add(searchButton);
  // }
  //

}
