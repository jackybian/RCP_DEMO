package com.ost.jacky.demo;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.ost.jacky.demo.editors.editorInput.FileEditorInput;
import com.ost.jacky.demo.util.PluginUtil;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewLabelProvider;

public class View1 extends ViewPart{
	private TreeViewer treeViewer;

	@Override
	public void createPartControl(Composite parent) {
	    treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
	    // 设置内容提供其和标签提供器
//	    treeViewer.setContentProvider(new ViewContentProvider());
//	    treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(
//                new ViewLabelProvider(createImageDescriptor())));
	    // 读入数据
//	    treeViewer.setInput(File.listRoots());
	}
	


	
	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}
}
