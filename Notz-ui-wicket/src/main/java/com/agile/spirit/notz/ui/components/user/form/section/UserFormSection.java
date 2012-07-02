package com.agile.spirit.notz.ui.components.user.form.section;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.agile.spirit.notz.domain.User;
import com.agile.spirit.notz.ui.NotzPanel;


public abstract class UserFormSection extends NotzPanel {

  private static final long serialVersionUID = 1L;

  /* Components */
  //	FeedbackPanel feedbackPanel;
  String feedbackPanelId;

  public UserFormSection(String id, String feedbackPanelId, final IModel<User> model) {
    super(id);
    this.setDefaultModel(new CompoundPropertyModel<User>(model));
    this.feedbackPanelId = feedbackPanelId;

    buildFeedbackPanel();
  }

  private void buildFeedbackPanel() {
    IFeedbackMessageFilter filter = new ContainerFeedbackMessageFilter(this);
    FeedbackPanel feedbackPanel = new FeedbackPanel(feedbackPanelId, filter);
    feedbackPanel.setOutputMarkupId(true);
    add(feedbackPanel);
  }

  public abstract void resetFields();

}
