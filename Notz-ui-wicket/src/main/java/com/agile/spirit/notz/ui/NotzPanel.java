package com.agile.spirit.notz.ui;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Panel;

public class NotzPanel extends Panel {

  private static final long serialVersionUID = -4081768740427027177L;

  public NotzPanel(String id) {
    super(id);
  }

  public NotzSession getNotzSession() {
    return ((NotzPage) getPage()).getNotzSession();
  }

  public ModalWindow getModal() {
    return ((NotzPage) getPage()).getModal();
  }

  public void configureModal(Panel content, int width) {
    ModalWindow modal = getModal();
    modal.setInitialWidth(width);
    modal.setContent(content);
  }

  public void showModal(AjaxRequestTarget target) {
    ((NotzPage) getPage()).showModal(target);
  }

  public void closeModal(AjaxRequestTarget target) {
    ((NotzPage) getPage()).closeModal(target);
  }

}
