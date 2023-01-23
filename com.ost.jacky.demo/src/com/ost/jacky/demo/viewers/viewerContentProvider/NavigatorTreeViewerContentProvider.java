package com.ost.jacky.demo.viewers.viewerContentProvider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.ost.jacky.demo.navigator.ITreeElement;

public class NavigatorTreeViewerContentProvider implements ITreeContentProvider{
	  public NavigatorTreeViewerContentProvider(){
		    //最好是写上一个默认的构造器
		  }

		  @Override
		  public void dispose() {

		  }

		  @Override
		  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		  }

		  @Override//当输入是设定的元素时，返回查看器中显示的元素
		  public Object[] getElements(Object inputElement) {
		    if (inputElement instanceof List) {
		      return ((List) inputElement).toArray();
		    }else{
		      return new Object[0];//为什么在不是list的时候要返回的是一个空的Object数组呢？
		    }
		  }

		  @Override//得到指定父元素的子元素
		  public Object[] getChildren(Object parentElement) {
		    ITreeElement treeElement = (ITreeElement)parentElement;
		    List children = treeElement.getChildren();
		    if(children == null || children.isEmpty()){
		      return new Object[0];
		    }else{
		      return children.toArray();
		    }
		  }

		  @Override//没有去实现
		  public Object getParent(Object element) {
		    return null;
		  }

		  @Override//是否有子节点
		  public boolean hasChildren(Object element) {
		    ITreeElement treeElement = (ITreeElement)element;
		    List children = treeElement.getChildren();
		    if(children == null || children.isEmpty()){
		      return false;
		    }else{
		      return true;
		    }
		  }
}
