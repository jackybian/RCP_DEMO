package com.ost.jacky.demo.viewers;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.ViewPart;

import com.ost.jacky.demo.dao.PatientDAO;
import com.ost.jacky.demo.viewers.viewerContentProvider.PatientInfoTableViewerContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.PatientInfoTableViewerLabelProvider;


public class ContentView extends EditorPart{
	public static final String ID = "com.ost.jacky.demo.content";
	
	private PatientDAO patientDAO;
	
	private TableViewer tableViewer;
	


	@Override
	public void createPartControl(Composite parent) {
		System.out.println("ContentView createPartControl start=====");
		System.out.println("name=" + this.getEditorInput().getName());
		patientDAO = new PatientDAO();
	    // 首先创建一个ViewForm对象，它方便于控件的布局
	    ViewForm viewForm = new ViewForm(parent, SWT.NONE);
	    // 布局
	    viewForm.setLayout(new FillLayout());
	    // 创建TableViewer
	    createTableViewer(viewForm);
	    tableViewer.setContentProvider(new PatientInfoTableViewerContentProvider());
	    tableViewer.setLabelProvider(new PatientInfoTableViewerLabelProvider());
	    tableViewer.setInput(patientDAO.getPatientInfoList());
	    //tableViewer.setSorter(new PatientInfoSorter());

	    // 添加编辑器的工具栏，包括了 修改，删除，刷新 三个按钮
	    ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT);
	    ToolBarManager toolBarManager = new ToolBarManager(toolBar);
	    toolBarManager.add(new AddPatientAction());
//	    toolBarManager.add(new DeletePatientAction());
//	    toolBarManager.add(new ModifyPatientAction());
//	    toolBarManager.add(new RefreshAction());
	    toolBarManager.update(true);
	    // This brings the underlying widgets up to date with any changes.

	    // 设置viewform
	    viewForm.setTopLeft(toolBar);
	    viewForm.setContent(tableViewer.getControl());
	}


	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}
	
	private List<String> createInitialDataModel() {
		return Arrays.asList("One", "Two","Content");
	}


	@Override
	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// TODO Auto-generated method stub
		this.setSite(site);
		this.setInput(input);
	}


	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	 private void createTableViewer(Composite composite) {
		 System.out.println("ContentView createTableViewer start=====");
//	    tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
	    tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
	    Table table = tableViewer.getTable();
	    // 设置显示标题
	    table.setHeaderVisible(true);
	    // 设置显示表格线
	    table.setLinesVisible(true);
	    // 设置表格的布局 TableColumnLayout
	    //TODO：重点注意下面的布局设置！不能有，它会导致很多问题，例如数组溢出，还有界面放大缩小出现
	    //延迟！很多问题！
//	    table.setLayout(new TableColumnLayout());
	    // 建立一列
	    TableColumn tc1 = new TableColumn(table, SWT.LEFT);
	    // 设置列标题
	    tc1.setText("姓名");
	    // 设置列宽
	    tc1.setWidth(60);
	    // 添加排序器（列被点击时触发）
	    // 注意：widgetSelected方法的末尾一定要refresh，这样可以保证不用手动刷新！
	    // 还有，参数传一个SelectionAdapter就可以了！
	    tc1.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        tableViewer.refresh();
	      }
	    });

	    TableColumn tc2 = new TableColumn(table, SWT.LEFT);
	    tc2.setText("年龄");
	    tc2.setWidth(100);
	    tc2.addSelectionListener(new SelectionAdapter() {
	      @Override
	      public void widgetSelected(SelectionEvent e) {
	        tableViewer.refresh();
	      }
	    });






	  }
	 
	  // 注意：以下三个操作定义为内部类！
	  // 原因是它们需要访问当前类的 TableViewer 对象，所以放在这个类里实现起来很方便！
//	  class ModifyPatientAction extends Action {
//	    public ModifyPatientAction() {
//	      // 设置提示文本
//	      this.setToolTipText("Modify Patient Information");
//	      // 设置图标
//	      this.setImageDescriptor(Activator.getImageDescriptor("/icons/Applications.ico"));
//	    }
//	    public void run() {
//	      IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
//	      Patient patient = (Patient) selection.getFirstElement();
//	      if (patient == null) {
//	        return;
//	      }
//	      // 调出 修改病人住院信息的dialog
//	      PatientFactory.setInstance(patient);
//
//	      //创建自定义的向导
//	      AddPatientInfoWizard wizard = new AddPatientInfoWizard();
//	      wizard.setType(PluginUtil.MODIFY);
//	      //设置向导中的patient是当前选中的patient
////	      wizard.setPatient(patient);//我想避免使用这些get和set
////	      PatientFactory.getInstance() = patient;//这样的代码不行！报错：左边不是变量
//	      //解决：在PatientFactory中添加一个setInstance方法
////	      
//	      //显示向导对话框
//	      WizardDialog dialog = new WizardDialog(Display.getDefault().getShells()[0], wizard);
//	      //设置对话框大小
//	      dialog.setPageSize(-1, 150);//注意：高度要足够的大，否则有些组件显示不出来
//	      //打开
//	      dialog.open();  
//	      //修改完成之后要刷新列表
//	      tableViewer.setInput(patientDAO.getPatientInfoList());
//	      tableViewer.refresh();
//	    }
//	  }

	  class AddPatientAction extends Action {
	    public AddPatientAction() {
	      // 设置提示文本
	      this.setToolTipText("Add Patient Information");
	      // 设置图标
	      //this.setImageDescriptor(Activator.getImageDescriptor("/icons/small/add.gif"));
	    }

	    public void run() {
	      // 调出 创建新的病人住院信息的dialog
	      //创建自定义的向导
//	      AddPatientInfoWizard wizard = new AddPatientInfoWizard();
//	      wizard.setType(PluginUtil.ADD);
//	      //注意：每次打开向导页面时都要对PatientFactory的instance进行设置，不然会出现紊乱
////	      PatientFactory.setInstance(null);  //不能设置为null，不然后面的操作（判断操作类型，和设置值）
////	      PatientFactory.setInstance(new Patient());
//	      //显示向导对话框
//	      WizardDialog dialog = new WizardDialog(Display.getDefault().getShells()[0], wizard);
//	      //设置对话框大小
//	      dialog.setPageSize(-1, 150);//注意：高度要足够的大
//	      //打开
//	      dialog.open();
	      System.out.println("do add action");
	      //添加完成之后要刷新列表
//	      tableViewer.refresh();//这个方法不行！
	      //还是得重新取出数据并刷新现实
	      tableViewer.setInput(patientDAO.getPatientInfoList());
	      tableViewer.refresh();
	    }
	  }

//	  class DeletePatientAction extends Action {
//
//	    public DeletePatientAction() {
//	      // 设置提示文本
//	      this.setToolTipText("Delete Patient Information");
//	      // 设置图标
//	      this.setImageDescriptor(Activator.getImageDescriptor("/icons/small/delete.gif"));
//	    }
//
//	    public void run() {
//	      IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
//	      Patient patient = (Patient) selection.getFirstElement();
//	      if (patient == null) {
//	        return;
//	      }
//	      if (MessageDialog.openConfirm(null, "Confirm to delete", "Are you sure to delete?")) {
//	        if (patientDAO.deletePatient(patient)) {
//	          @SuppressWarnings("unchecked")
//	          List<Patient> list = (List<Patient>) tableViewer.getInput();
//	          list.remove(patient);// 这里删除便于刷新时是最新的数据
//	          tableViewer.remove(patient);// 实时更新
//	        } else {//删除出错时提示
//	          MessageDialog.openInformation(null, "Error Information", "Fail to delete the Patient!");
//	        }
//	      }
//	    }
//
//	  }

//	  class RefreshAction extends Action {
//	    public RefreshAction() {
//	      // 设置提示文本
//	      this.setToolTipText("Refresh Patient Information");
//	      // 设置图标
//	      this.setImageDescriptor(Activator.getImageDescriptor("/icons/Refresh.ico"));
//	    }
//	    public void run() {
//	      tableViewer.setInput(patientDAO.getPatientInfoList());
//	      tableViewer.refresh();
//	    }
//	  }
}
