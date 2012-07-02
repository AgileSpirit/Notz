package com.agile.spirit.notz.ui;

import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;

public class NotzPage extends WebPage {

  private static final long serialVersionUID = 1L;

  ModalWindow modal;
  
  public NotzPage() {
    super();
    buildModal();
  }

  public NotzSession getWicketSession() {
    return (NotzSession) getSession();
  }
  
  public ModalWindow getModal() {
    return modal;
  }
  
  private void buildModal() {
    modal = new ModalWindow("modal"); 
    modal.setUseInitialHeight(false);
    modal.setHeightUnit("px");
    modal.setWidthUnit("px");
    modal.setResizable(false);
    modal.setCookieName("wicket-tips/styledModal");
    modal.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
    modal.setMaskType(ModalWindow.MaskType.SEMI_TRANSPARENT);
    add(modal);
  }

}
