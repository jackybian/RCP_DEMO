package com.ost.jacky.demo.wizards;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ost.jacky.demo.editors.FileEditor;
import com.ost.jacky.demo.util.QueryCondition;


public class DeleteFilePreQueryWizardPage extends WizardPage implements ModifyListener{
	
	private Text preConditions;
	private QueryCondition queryCondition;
	public DeleteFilePreQueryWizardPage(QueryCondition queryCondition) {
		super("");
		setTitle("Delete File Wizard");
		setMessage("Attention:Please input the following information!", IMessageProvider.INFORMATION);
		this.queryCondition = queryCondition;
	}
	protected DeleteFilePreQueryWizardPage(String pageName) {
		super(pageName);
	}

	
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		preConditions = new Text(composite,  SWT.WRAP|SWT.V_SCROLL);
		preConditions.setBounds(200, 300, 600, 1000);
		preConditions.addModifyListener(this);

//		new Label(composite, SWT.NONE).setText("后置匹配");
//		textAge = new Text(composite, SWT.MULTI);
//		textAge.setLayoutData(gridData);
//		textAge.setBounds(10, 8, 325, 210);
//		textAge.addModifyListener(this);
//
//		new Label(composite, SWT.NONE).setText("包含");
//		textPhone = new Text(composite, SWT.MULTI);
//		textPhone.setLayoutData(gridData);
//		textPhone.setBounds(10, 8, 325, 210);
//		textPhone.addModifyListener(this);
		setControl(composite);		
		setPageComplete(false);
	}
	public void modifyText(ModifyEvent e) {
		queryCondition.setPreConditions(preConditions.getText());
		setPageComplete(true);
	}
}
