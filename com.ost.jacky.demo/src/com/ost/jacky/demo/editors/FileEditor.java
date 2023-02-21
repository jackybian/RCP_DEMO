package com.ost.jacky.demo.editors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.ost.jacky.demo.editors.editorInput.FileEditorInput;
import com.ost.jacky.demo.util.QueryCondition;
import com.ost.jacky.demo.viewers.viewerContentProvider.FileInfoTableViewerContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.FileInfoTableViewerLabelProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewLabelProvider;
import com.ost.jacky.demo.wizards.DeleteFileWizard;
import com.ost.jacky.demo.wizards.SearchFileWizard;

public class FileEditor extends EditorPart {

	private TableViewer tableViewer;

	private List<String> list;

	private List<String> searchList;

	private Map map;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	private boolean sort;

	public TableViewer getViewer() {
		return tableViewer;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		FileEditorInput fileEditorInput = (FileEditorInput) this.getEditorInput();
		System.out.println("createPartControl fileName: " + fileEditorInput.getFileName());
		ViewForm viewForm = new ViewForm(parent, SWT.NONE);
		viewForm.setLayout(new FillLayout());
		createTableViewer(viewForm);
		tableViewer.setContentProvider(new FileInfoTableViewerContentProvider());
		tableViewer.setLabelProvider(new FileInfoTableViewerLabelProvider());
		list = new ArrayList<String>();
		map = new HashMap();
		try {
			FileInputStream fileInput = new FileInputStream(fileEditorInput.getFileName());
			ObjectInputStream in = new ObjectInputStream(fileInput);
			map = (HashMap) in.readObject();
			for (Object k : map.keySet()) {
				list.add(k.toString());
			}

		} catch (Exception ex) {

		}
		tableViewer.setInput(list);
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		toolBarManager.add(new DeletePatientAction(fileEditorInput.getFileName(), list));
		toolBarManager.add(new SaveAction(fileEditorInput.getFileName(), list, map));
		toolBarManager.add(new SearchAction(fileEditorInput.getFileName(), list));
		toolBarManager.add(new RefreshAction(fileEditorInput.getFileName(), list));
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
		tc1.setText("Name");
		tc1.setWidth(1000);
		tc1.setResizable(true);
	}

	@Override
	public void setFocus() {
	}

	class DeletePatientAction extends Action {

		private String fileName;

		private List<String> list;

		private QueryCondition queryCondition;

		private ImageDescriptor createImageDescriptor() {
			Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
			URL url = FileLocator.find(bundle, new Path("/icons/small/delete.gif"), null);
			return ImageDescriptor.createFromURL(url);
		}

		public DeletePatientAction(String fileName, List<String> list) {
			this.setToolTipText("Delete File Name");
			this.setImageDescriptor(createImageDescriptor());
			this.fileName = fileName;
			this.list = list;
		}

		public void run() {
			DeleteFileWizard wizard = new DeleteFileWizard();
			wizard.setList(list);
			WizardDialog dialog = new WizardDialog(Display.getDefault().getShells()[0], wizard);
			dialog.setPageSize(500, 400);
			dialog.open();
			tableViewer.setInput(wizard.getList());
			tableViewer.refresh();
		}

	}

	class SaveAction extends Action {

		private String fileName;

		private List<String> list;

		private QueryCondition queryCondition;

		private Map map;

		private ImageDescriptor createImageDescriptor() {
			Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
			URL url = FileLocator.find(bundle, new Path("/icons/small/save.ico"), null);
			return ImageDescriptor.createFromURL(url);
		}

		public SaveAction(String fileName, List<String> list, Map map) {
			this.setToolTipText("Save File");
			this.setImageDescriptor(createImageDescriptor());
			this.fileName = fileName;
			this.list = list;
			this.map = map;
		}

		public void run() {
			FileDialog dialog = new FileDialog(Display.getDefault().getShells()[0], SWT.SAVE);
			String selectedDir = dialog.open();
			System.out.println("选择文件夹：" + selectedDir);
			Map newMap = new HashMap();
			for (String k : list) {
				newMap.put(k, map.get(k));
			}
			try {
				FileOutputStream fileOut = new FileOutputStream(selectedDir);
				try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
					out.writeObject(newMap);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	class SearchAction extends Action {

		private String fileName;

		private List<String> searchList;

		private List<String> list;

		private List<String> resultList;

		private QueryCondition queryCondition;

		private ImageDescriptor createImageDescriptor() {
			Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
			URL url = FileLocator.find(bundle, new Path("/icons/small/Search.ico"), null);
			return ImageDescriptor.createFromURL(url);
		}

		public SearchAction(String fileName, List<String> list) {
			this.setToolTipText("Delete File Name");
			this.setImageDescriptor(createImageDescriptor());
			this.fileName = fileName;
			this.list = list;
		}

		public void run() {
			searchList = new ArrayList<String>();
			SearchFileWizard wizard = new SearchFileWizard(searchList);
			WizardDialog dialog = new WizardDialog(Display.getDefault().getShells()[0], wizard);
			dialog.setPageSize(100, 100);
			dialog.open();
			System.out.println("searchList size = " + searchList.size());
			if (searchList.size() > 0) {
				resultList = new ArrayList<>();
				String reg = searchList.get(searchList.size() - 1);
				for (String value : list) {
					if (value.contains(reg)) {
						resultList.add(value);
					}
				}
				tableViewer.setInput(resultList);
				tableViewer.refresh();
			}
		}

	}

	class RefreshAction extends Action {

		private String fileName;

		private List<String> searchList;

		private List<String> list;

		private List<String> resultList;

		private QueryCondition queryCondition;

		private ImageDescriptor createImageDescriptor() {
			Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
			URL url = FileLocator.find(bundle, new Path("/icons/small/Refresh.ico"), null);
			return ImageDescriptor.createFromURL(url);
		}

		public RefreshAction(String fileName, List<String> list) {
			this.setToolTipText("Refresh File Name");
			this.setImageDescriptor(createImageDescriptor());
			this.fileName = fileName;
			this.list = list;
		}

		public void run() {
			try {
				FileInputStream fileInput = new FileInputStream(fileName);
				ObjectInputStream in = new ObjectInputStream(fileInput);
				Map map = (HashMap) in.readObject();
				if (null == list) {
					list = new ArrayList<>();
				}
				list.clear();
				for (Object k : map.keySet()) {
					list.add(k.toString());
				}
			} catch (Exception ex) {

			}
			tableViewer.setInput(list);
			tableViewer.refresh();
		}

	}
}
