package com.ost.jacky.demo.viewers.viewerContentProvider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileInfoTableViewerContentProvider implements IStructuredContentProvider{
	@Override
	public Object[] getElements(Object inputElement) {
		return ((List)inputElement).toArray();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
