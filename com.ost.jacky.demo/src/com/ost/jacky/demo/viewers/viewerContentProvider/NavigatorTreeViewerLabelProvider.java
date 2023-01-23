package com.ost.jacky.demo.viewers.viewerContentProvider;


import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.ost.jacky.demo.navigator.NavigatorEntityElement;

public class NavigatorTreeViewerLabelProvider implements ILabelProvider{
	public NavigatorTreeViewerLabelProvider(){
	    //最好是写上一个默认的构造器
	  }

	  @Override
	  public void addListener(ILabelProviderListener listener) {

	  }

	  @Override
	  public void dispose() {

	  }

	  @Override
	  public boolean isLabelProperty(Object element, String property) {
	    return false;
	  }

	  @Override
	  public void removeListener(ILabelProviderListener listener) {

	  }

	  //重点：学会得到系统提供的图片的方法：PlatformUI.getWorkbench().getSharedImages().getImage( ISharedImages.XX)
	  @Override//要显示的图片
	  public Image getImage(Object element) {
	    String image1 = ISharedImages.IMG_OBJ_FOLDER;
	    String image2 = ISharedImages.IMG_OBJ_FILE;
	    NavigatorEntityElement entityElement = (NavigatorEntityElement) element;
	    if(entityElement.hasChildren()){
	      return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
	    }else{
	      return PlatformUI.getWorkbench().getSharedImages().getImage(image2);
	    }
	  }

	  @Override//要显示的文本
	  public String getText(Object element) {
	    NavigatorEntityElement entityElement = (NavigatorEntityElement) element;
	    return entityElement.getName();
	  }
}
