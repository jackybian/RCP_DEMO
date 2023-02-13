package com.ost.jacky.demo.editors;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.ost.jacky.demo.editors.editorInput.FileEditorInput;
import com.yinger.patientims.views.viewerProvider.PatientInfoTableViewerContentProvider;
import com.yinger.patientims.views.viewerProvider.PatientInfoTableViewerLabelProvider;


public class PatientInfoEditor extends EditorPart {

	private TableViewer tableViewer;
	
	private boolean sort;

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
		FileEditorInput fileEditorInput = (FileEditorInput)this.getEditorInput();
		System.out.println("createPartControl fileName: " + fileEditorInput.getFileName());
		ViewForm viewForm = new ViewForm(parent, SWT.NONE);
		viewForm.setLayout(new FillLayout());
		createTableViewer(viewForm);
		tableViewer.setContentProvider(new PatientInfoTableViewerContentProvider());
		tableViewer.setLabelProvider(new PatientInfoTableViewerLabelProvider());
		List<String> fileList = new ArrayList<String>();
		try {
			FileInputStream fileInput = new FileInputStream(fileEditorInput.getFileName());
			ObjectInputStream in = new ObjectInputStream(fileInput);
			Map map = (HashMap) in.readObject();
			for (Object k: map.keySet()) {
				fileList.add(k.toString());
			}
		} catch (Exception ex) {
			
		}
		tableViewer.setInput(fileList);
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		toolBarManager.add(new AddPatientAction(this));
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
	
	class AddPatientAction extends Action {
		private PatientInfoEditor editor;
		
		public AddPatientAction(PatientInfoEditor editor) {
			this.setToolTipText("Add Patient Information");
		}
		public void run() {
			System.out.println("AddPatientAction work");
			FileEditorInput fileEditorInput = (FileEditorInput)editor.getEditorInput();
			List<String> fileList = new ArrayList<String>();
			try {
				FileInputStream fileInput = new FileInputStream(fileEditorInput.getFileName());
				ObjectInputStream in = new ObjectInputStream(fileInput);
				Map map = (HashMap) in.readObject();
				for (Object k: map.keySet()) {
					fileList.add(k.toString());
				}
			} catch (Exception ex) {
				
			}
			tableViewer.setInput(fileList);
			tableViewer.refresh();
		}
	}
	
}
