package com.agile.spirit.notz.ui.components.user.menu;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.pages.home.HomePage;
import com.agile.spirit.notz.ui.pages.user.settings.UserSettingsPage;

public class AccountMenuBar extends NotzPanel {

  private static final long serialVersionUID = 1L;

  public AccountMenuBar(String id) {
    super(id);
    buildUserSettingsLink();
    buildLogoutLink();
  }

  @Override
  protected void onConfigure() {
    setVisible(getNotzSession().isUserLoggedIn());
  }

  private void buildUserSettingsLink() {
    BookmarkablePageLink<String> userSettingsLink = new BookmarkablePageLink<String>("userSettingsLink", UserSettingsPage.class);
    add(userSettingsLink);
  }

  private void buildLogoutLink() {
    Link<String> logoutLink = new Link<String>("logoutLink") {

      private static final long serialVersionUID = 1L;

      @Override
      public void onClick() {
        getSession().invalidateNow();
        setResponsePage(HomePage.class);
      }
    };
    add(logoutLink);
  }

}
