package com.ost.jacky.demo.navigator;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IEditorInput;
public class NavigatorEntityElement implements ITreeElement {
	  private String name;// 节点名称
	  private int level = 0;// 节点深度，0表示根节点，是默认值
	  private List children = new ArrayList();// 节点孩子
	  private IEditorInput editorInput;// 节点的IEditorInput对象
	  //IEditorInput是新增的，在接口中没有定义，它在元素上绑定一个editor，这样就可以知道双击这个节点需要打开的editor

	  public NavigatorEntityElement() {

	  }

	  public NavigatorEntityElement(String name) {
	    this.name = name;
	  }

	  public NavigatorEntityElement(int level, String name) {
	    this(name);
	    this.level = level;
	  }

	  public int getLevel() {
	    return level;
	  }

	  public void setLevel(int level) {
	    this.level = level;
	  }

	  public IEditorInput getEditorInput() {
	    return editorInput;
	  }

	  public void setEditorInput(IEditorInput editorInput) {
	    this.editorInput = editorInput;
	  }

	  @Override
	  public void setName(String name) {
	    this.name = name;
	  }

	  @Override
	  public String getName() {
	    return name;
	  }

	  @Override
	  public void setChildren(List children) {
	    this.children = children;
	  }

	  @Override
	  public List getChildren() {
	    return children;
	  }

	  @Override
	  public boolean hasChildren() {
	    if (children.size() > 0) {
	      return true;
	    }
	    return false;
	  }

	  @Override
	  public void addChildren(ITreeElement iElement) {
	    children.add(iElement);
	  }
}
