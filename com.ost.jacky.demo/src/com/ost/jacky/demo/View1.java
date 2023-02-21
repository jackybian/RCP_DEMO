package com.ost.jacky.demo;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.ost.jacky.demo.editors.editorInput.FileEditorInput;
import com.ost.jacky.demo.util.PluginUtil;
import com.ost.jacky.demo.viewers.viewerContentProvider.FileInfoTableViewerContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.FileInfoTableViewerLabelProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewLabelProvider;

public class View1 extends ViewPart{
	private TableViewer tableViewer;

	private List<String> list;
	@Override
	public void createPartControl(Composite parent) {
		ViewForm viewForm = new ViewForm(parent, SWT.NONE);
		viewForm.setLayout(new FillLayout());
		createTableViewer(viewForm);
	    // 设置内容提供其和标签提供器
		tableViewer.setContentProvider(new FileInfoTableViewerContentProvider());
		tableViewer.setLabelProvider(new FileInfoTableViewerLabelProvider());
	    // 读入数据
		if (null == list) {
			list = new ArrayList<>();
		}
		list.add("1");
		tableViewer.setInput(list);
		viewForm.setContent(tableViewer.getControl());
	}
	
	private void createTableViewer(Composite composite) {
		tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		tc1.setText("Name");
		tc1.setWidth(1000);
		tc1.setResizable(true);
	}

	
	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}
	
	public TableViewer getTableViewer() {
		return tableViewer;
	}
}
