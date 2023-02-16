package com.ost.jacky.demo.wizards;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ost.jacky.demo.editors.PatientInfoEditor;
import com.ost.jacky.demo.util.QueryCondition;


public class DeleteFileWizardPage extends WizardPage implements ModifyListener{
	
	private Text textName;
	private Text textAge;
	private Text textPhone;
	private String type;
	private QueryCondition queryCondition;
	public DeleteFileWizardPage(QueryCondition queryCondition) {
		super("");
		setTitle("Delete File Wizard");
		setMessage("Attention:Please input the following information!", IMessageProvider.INFORMATION);
		this.queryCondition = queryCondition;
	}
	protected DeleteFileWizardPage(String pageName) {
		super(pageName);
		this.type = pageName;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		new Label(composite, SWT.NONE).setText("前置匹配");
		textName = new Text(composite,  SWT.MULTI);
		textName.setLayoutData(gridData);
		textName.setBounds(10, 8, 325, 210);
		textName.addModifyListener(this);

		new Label(composite, SWT.NONE).setText("后置匹配");
		textAge = new Text(composite, SWT.MULTI);
		textAge.setLayoutData(gridData);
		textAge.setBounds(10, 8, 325, 210);
		textAge.addModifyListener(this);

		new Label(composite, SWT.NONE).setText("包含");
		textPhone = new Text(composite, SWT.MULTI);
		textPhone.setLayoutData(gridData);
		textPhone.setBounds(10, 8, 325, 210);
		textPhone.addModifyListener(this);
		setControl(composite);		
		setPageComplete(false);
	}
	public void modifyText(ModifyEvent e) {
		queryCondition.setPreConditions(textName.getText());
		queryCondition.setPostConditions(textAge.getText());
		queryCondition.setContainConditions(textPhone.getText());
		setPageComplete(true);
	}
	public void save() {

	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
