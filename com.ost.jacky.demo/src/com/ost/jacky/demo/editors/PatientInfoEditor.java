package com.ost.jacky.demo.editors;

import java.util.List;

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

import com.ost.jacky.demo.dao.PatientDAO;
import com.ost.jacky.demo.viewers.viewerContentProvider.PatientInfoTableViewerContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.PatientInfoTableViewerLabelProvider;


public class PatientInfoEditor extends EditorPart {

	private PatientDAO patientDAO;

	// һ��鿴�����ĸ��ؼ����ࣩ����ʾ��������Ҫ��һ����Ӧ�Ĳ鿴�����ֶ�
	private TableViewer tableViewer;// ���鿴��
	private boolean sort;// ��ʶ����ķ�ʽ

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	// �������Ҫ�޸ģ���д��
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
	// ��������������ñ༭����Ҫ��ʾ������
	public void createPartControl(Composite parent) {
		// ���ȳ�ʼ��һ��PatientDAO
		patientDAO = new PatientDAO();

		// ���ȴ���һ��ViewForm�����������ڿؼ��Ĳ���
		ViewForm viewForm = new ViewForm(parent, SWT.NONE);
		// ����
		viewForm.setLayout(new FillLayout());
		// ����TableViewer
		createTableViewer(viewForm);
		tableViewer.setContentProvider(new PatientInfoTableViewerContentProvider());
		tableViewer.setLabelProvider(new PatientInfoTableViewerLabelProvider());
		tableViewer.setInput(patientDAO.getPatientInfoList());
//		tableViewer.setSorter(new PatientInfoSorter());

		// ��ӱ༭���Ĺ������������� �޸ģ�ɾ����ˢ�� ������ť
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		toolBarManager.add(new AddPatientAction());
//		toolBarManager.add(new DeletePatientAction());
//		toolBarManager.add(new ModifyPatientAction());
//		toolBarManager.add(new RefreshAction());
		toolBarManager.update(true);
		// This brings the underlying widgets up to date with any changes.
		
		// ����viewform
		viewForm.setTopLeft(toolBar);
		viewForm.setContent(tableViewer.getControl());
	}

	private void createTableViewer(Composite composite) {
//		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		// ������ʾ����
		table.setHeaderVisible(true);
		// ������ʾ�����
		table.setLinesVisible(true);
		// ���ñ��Ĳ��� TableColumnLayout
		//TODO���ص�ע������Ĳ������ã������У����ᵼ�ºܶ����⣬����������������н���Ŵ���С����
		//�ӳ٣��ܶ����⣡
//		table.setLayout(new TableColumnLayout());
		// ����һ��
		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		// �����б���
		tc1.setText("Name");
		// �����п�
		tc1.setWidth(60);
		// ������������б����ʱ������
		// ע�⣺widgetSelected������ĩβһ��Ҫrefresh���������Ա�֤�����ֶ�ˢ�£�
		// ���У�������һ��SelectionAdapter�Ϳ����ˣ�

		TableColumn tc2 = new TableColumn(table, SWT.LEFT);
		tc2.setText("Age");
		tc2.setWidth(100);

	}

	@Override
	public void setFocus() {
	}
	
	class AddPatientAction extends Action {
		public AddPatientAction() {
			this.setToolTipText("Add Patient Information");
		}

		public void run() {
			System.out.println("AddPatientAction work");
			tableViewer.setInput(patientDAO.getPatientInfoList());
			tableViewer.refresh();
		}
	}
	
}
