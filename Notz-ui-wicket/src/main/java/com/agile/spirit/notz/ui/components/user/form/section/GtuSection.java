package com.agile.spirit.notz.ui.components.user.form.section;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.agile.spirit.notz.domain.User;


public class GtuSection extends UserFormSection {

  private static final long serialVersionUID = 8941621577183541815L;

  /* Components */
  CheckBox gtuInput;

  public GtuSection(String id, IModel<User> model) {
    super(id, "gtuFeedback", model);
    buildGtuInput();
  }

  private void buildGtuInput() {
    gtuInput = new CheckBox("gtu", new Model<Boolean>());
    gtuInput.setRequired(true);
    add(gtuInput);
  }

  @Override
  public void resetFields() {
  }

}
