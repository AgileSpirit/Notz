package com.agile.spirit.notz.ui.components.util;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class DefaultWhenNullTextField<T> extends TextField<T> {

  private final DefaultWhenNullModel<T> dwnModel;
  private boolean isDwnModelChecked = false;
  private final AttributeModifier classAttributeModifier;

  public DefaultWhenNullTextField(final String id, final IModel<T> model, final T defaultValue, final boolean required) {
    super(id, model);
    setRequired(required);

    dwnModel = new DefaultWhenNullModel<T>(model, defaultValue);
    setDefaultModel(dwnModel);

    classAttributeModifier = new AttributeModifier("class", new Model<String>() {
      @Override
      public String getObject() {
        if (dwnModel.getNestedModelObject() == null) {
          return "emptyField";
        }
        return "filledField";
      }
    });
    add(classAttributeModifier);

    add(new AjaxEventBehavior("onFocus") {

      @Override
      protected void onEvent(AjaxRequestTarget target) {
        if (isDwnModelChecked == false && dwnModel.getNestedModelObject() == null) {
          isDwnModelChecked = true;
          setModelObject(null);
          target.add(DefaultWhenNullTextField.this);
        }
      }
    });

    add(new AjaxEventBehavior("focusout") {

      @Override
      protected void onEvent(AjaxRequestTarget target) {
        isDwnModelChecked = false;
        setModelObject(dwnModel.getObject());
        target.add(DefaultWhenNullTextField.this);
      }
    });
    // add(new OnChangeAjaxBehavior() {
    //
    // @Override
    // protected void onUpdate(AjaxRequestTarget target) {
    // target.add(DefaultWhenNullTextField.this);
    // }
    // });
  }
}
