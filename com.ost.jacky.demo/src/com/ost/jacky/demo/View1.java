package com.ost.jacky.demo;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.ost.jacky.demo.editors.FileEditor;
import com.ost.jacky.demo.editors.editorInput.FileEditorInput;
import com.ost.jacky.demo.util.PluginUtil;
import com.ost.jacky.demo.util.QueryCondition;
import com.ost.jacky.demo.viewers.viewerContentProvider.FileInfoTableViewerContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.FileInfoTableViewerLabelProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewLabelProvider;

public class View1 extends ViewPart {
	private TableViewer tableViewer;

	private Set<String> delFileSet;

	@Override
	public void createPartControl(Composite parent) {
		ViewForm viewForm = new ViewForm(parent, SWT.NONE);
		viewForm.setLayout(new FillLayout());
		createTableViewer(viewForm);
		// 设置内容提供其和标签提供器
		tableViewer.setContentProvider(new FileInfoTableViewerContentProvider());
		tableViewer.setLabelProvider(new FileInfoTableViewerLabelProvider());
		// 读入数据
		if (null == delFileSet) {
			delFileSet = new HashSet<>();
		}
		List<String> list = new ArrayList<>();
		list.addAll(delFileSet);
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		tableViewer.setInput(list);
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		toolBarManager.add(new CancelAction(delFileSet));
		toolBarManager.update(true);
		viewForm.setTopLeft(toolBar);
		viewForm.setContent(tableViewer.getControl());
	}

	private void createTableViewer(Composite composite) {
		tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		tc1.setText("文件名");
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

	public Set<String> getDelFileSet() {
		return delFileSet;
	}

	public void setDelFileSet(Set<String> delFileSet) {
		this.delFileSet = delFileSet;
	}

	public void refreshTableViewer(Set<String> delFileSet) {
		this.delFileSet.addAll(delFileSet);
		List<String> list = new ArrayList<>();
		list.addAll(this.delFileSet);
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		tableViewer.setInput(list);
		tableViewer.refresh();
	}

	class CancelAction extends Action {

		private Set<String> delFileSet;

		private QueryCondition queryCondition;

		private ImageDescriptor createImageDescriptor() {
			Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
			URL url = FileLocator.find(bundle, new Path("/icons/Back.ico"), null);
			return ImageDescriptor.createFromURL(url);
		}

		public CancelAction(Set<String> delFileSet) {
			this.setToolTipText("Delete File Name");
			this.setImageDescriptor(createImageDescriptor());
			this.delFileSet = delFileSet;
		}

		public void run() {
			StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
			for (Object selectedObject : selection.toArray()) {
				delFileSet.remove(selectedObject);
			}
			List<String> list = new ArrayList<>();
			list.addAll(delFileSet);
			Collections.sort(list, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});

			tableViewer.setInput(list);
			tableViewer.refresh();
			IEditorPart viewPart = getSite().getPage().getActiveEditor();
			FileEditor fileEditor = (FileEditor) viewPart;
			List<String> fileList = fileEditor.getList();
			Set<String> set = new HashSet<>();
			fileList.forEach(item->{
				set.add(item);
			});
			for (Object selectedObject : selection.toArray()) {
				set.add(selectedObject.toString());
			}
			fileList.clear();
			fileList.addAll(set);
			Collections.sort(fileList, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			fileEditor.getViewer().setInput(fileList);
			fileEditor.getViewer().refresh();
//	        FileEditorInput fileEditorInput = new FileEditorInput();
//	        IWorkbenchPage workbenchPage = getViewSite().getPage();
//	        String editorID = null;
//	        // 其实这边是指定的类路径，要与viewer里面的ID，进行区分开来
//	        editorID = PluginUtil.PatientInfoEditor_ID;
//	        IEditorPart editorPart = workbenchPage.findEditor(fileEditorInput);
//	        if(editorPart != null){//已经打开了所需的编辑器
//	          workbenchPage.bringToTop(editorPart);
//	        }else {//没有打开就打开来
//	          try {
//	            editorPart = workbenchPage.openEditor(fileEditorInput, editorID);
//	          } catch (Exception e) {
//	            e.printStackTrace();
//	          }
//	        }
		}

	}

}
