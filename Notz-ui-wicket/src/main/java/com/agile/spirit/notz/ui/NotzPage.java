package com.agile.spirit.notz.ui;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;

import com.agile.spirit.notz.ui.components.user.menu.AccountMenuBar;
import com.agile.spirit.notz.ui.pages.home.HomePage;

public class NotzPage extends WebPage {

  private static final long serialVersionUID = 1L;

  ModalWindow modal;

  public NotzPage() {
    super();
    buildModal();
    buildAccountMenuBar();
  }

  @Override
  protected void onInitialize() {
    super.onInitialize();
    doOnInitialize();
  }

  /**
   * Add some behavior/logic during Initialization Phase. The default behavior is to check if the user is logged in and to redirect to
   * HomePage if not.
   */
  protected void doOnInitialize() {
    if (getNotzSession().isUserNotLoggedIn()) {
      setResponsePage(HomePage.class);
    }
  }

  public NotzSession getNotzSession() {
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
    modal.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
    modal.setMaskType(ModalWindow.MaskType.SEMI_TRANSPARENT);
    add(modal);
  }

  public void showModal(AjaxRequestTarget target) {
    if (target != null) {
      target.appendJavaScript("Wicket.Window.unloadConfirmation = false;");
    }
    modal.show(target);
  }

  public void closeModal(AjaxRequestTarget target) {
    modal.close(target);
  }

  private void buildAccountMenuBar() {
    AccountMenuBar accountMenuBar = new AccountMenuBar("accountMenuBar");
    add(accountMenuBar);
  }

}
