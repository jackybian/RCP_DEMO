package com.ost.jacky.demo.wizards;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.ost.jacky.demo.util.QueryCondition;

public class DeleteFilePostQueryWizardPage extends WizardPage implements ModifyListener{
	private Text postConditions;
	private QueryCondition queryCondition;
	public DeleteFilePostQueryWizardPage(QueryCondition queryCondition) {
		super("");
		setTitle("Delete File Wizard");
		setMessage("后缀查询，支持多行，用换行符分割", IMessageProvider.INFORMATION);
		this.queryCondition = queryCondition;
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		postConditions = new Text(composite,  SWT.WRAP|SWT.V_SCROLL);
		postConditions.setBounds(200, 300, 600, 1000);
		postConditions.addModifyListener(this);
		setControl(composite);		
		setPageComplete(false);
	}
	public void modifyText(ModifyEvent e) {
		queryCondition.setPostConditions(postConditions.getText());
		setPageComplete(true);
	}
}
