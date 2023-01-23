package com.ost.jacky.demo.navigator;

import java.util.List;

public interface ITreeElement {
	  public void setName(String name);

	  public String getName();

	  public void setChildren(List children);

	  public List getChildren();

	  public boolean hasChildren();

	  public void addChildren(ITreeElement iElement);
}
