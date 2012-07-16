package com.agile.spirit.notz.ui.components.util;

import org.apache.wicket.model.IModel;

public class DefaultWhenNullModel<T> implements IModel<T> {

  private static final long serialVersionUID = 1L;

  private final IModel<T> mNestedModel;
  private final T mDefaultValue;

  public DefaultWhenNullModel(IModel<T> nestedModel, T defaultValue) {
    mNestedModel = nestedModel;
    mDefaultValue = defaultValue;
  }

  @Override
  public T getObject() {
    T val = getNestedModelObject();
    return val == null ? mDefaultValue : val;
  }

  @Override
  public void setObject(T object) {
    mNestedModel.setObject(object);
  }

  @Override
  public void detach() {
    mNestedModel.detach();
  }

  /**
   * This method allows to get the real backing object of the (nested) model.
   * 
   * @return the nested model object
   */
  public T getNestedModelObject() {
    return mNestedModel.getObject();
  }
}
