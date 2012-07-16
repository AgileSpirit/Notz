package com.agile.spirit.notz.ui.components.util;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class DefaultWhenNullPasswordTextField<T> extends TextField<T> {

  private final DefaultWhenNullModel<T> dwnModel;

  public DefaultWhenNullPasswordTextField(final String id, final IModel<T> model, final T defaultValue, final boolean required) {
    super(id, model);
    setRequired(required);

    dwnModel = new DefaultWhenNullModel<T>(model, defaultValue);
    setDefaultModel(dwnModel);

  }

  @Override
  protected void onBeforeRender() {
    super.onBeforeRender();
  }
}
