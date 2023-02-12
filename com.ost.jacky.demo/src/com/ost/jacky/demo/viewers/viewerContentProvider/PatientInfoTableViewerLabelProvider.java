package com.ost.jacky.demo.viewers.viewerContentProvider;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.ost.jacky.demo.model.Patient;

public class PatientInfoTableViewerLabelProvider implements ITableLabelProvider{
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

	  @Override
	  public Image getColumnImage(Object element, int columnIndex) {
	    return null;
	  }

	  @Override//得到列中要显示的文本
	  public String getColumnText(Object element, int columnIndex) {
		return element.toString();
	  }
}
