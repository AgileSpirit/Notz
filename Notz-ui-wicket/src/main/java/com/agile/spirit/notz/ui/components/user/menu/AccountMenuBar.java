package com.agile.spirit.notz.ui.components.user.menu;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

import com.agile.spirit.notz.ui.NotzPanel;
import com.agile.spirit.notz.ui.pages.user.login.LoginPage;
import com.agile.spirit.notz.ui.pages.user.settings.UserSettingsPage;

public class AccountMenuBar extends NotzPanel {

  private static final long serialVersionUID = 1L;

  private WebMarkupContainer signupGroup;
  private WebMarkupContainer accountOptionsGroup;

  public AccountMenuBar(String id) {
    super(id);
    buildGroups();
  }

  private void buildGroups() {
    buildSignupGroup();
    buildAccountOptionsGroup();
  }

  private void buildSignupGroup() {
    signupGroup = new WebMarkupContainer("signupGroup");
    add(signupGroup);

    buildSignupLink();
  }

  private void buildSignupLink() {
    BookmarkablePageLink<String> signupLink = new BookmarkablePageLink<String>("signupLink", UserSettingsPage.class);
    signupGroup.add(signupLink);
  }

  private void buildAccountOptionsGroup() {
    accountOptionsGroup = new WebMarkupContainer("accountOptionsGroup");
    add(accountOptionsGroup);

    buildUserSettingsLink();
    buildLogoutLink();
  }

  private void buildUserSettingsLink() {
    BookmarkablePageLink<String> userSettingsLink = new BookmarkablePageLink<String>("userSettingsLink", UserSettingsPage.class);
    accountOptionsGroup.add(userSettingsLink);
  }

  private void buildLogoutLink() {
    Link<String> logoutLink = new Link<String>("logoutLink") {

      private static final long serialVersionUID = 1L;

      @Override
      public void onClick() {
        getSession().invalidateNow();
        setResponsePage(LoginPage.class);
      }
    };
    accountOptionsGroup.add(logoutLink);
  }

}
