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


public class DeleteFileWizardPage extends WizardPage implements ModifyListener{
	
	private Text textName;
	private Text textAge;
	private Text textPhone;
	private Text textAddress;
	private Combo comboSex;
	private String type;
	public DeleteFileWizardPage() {
		super("PatientBasicInfoWizardPage");
		setTitle("Add Patient Information");
		setMessage("Attention:Please input the following information!", IMessageProvider.INFORMATION);
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
		new Label(composite, SWT.NONE).setText("Name:");
		textName = new Text(composite, SWT.BORDER);
		textName.setLayoutData(gridData);
		textName.addModifyListener(this);
		
		new Label(composite, SWT.NONE).setText("Age:");
		textAge = new Text(composite, SWT.BORDER);
		textAge.setLayoutData(gridData);
		textAge.addModifyListener(this);

		new Label(composite, SWT.NONE).setText("Sex:");
		comboSex = new Combo(composite, SWT.READ_ONLY);
		comboSex.setItems(new String[]{"Male","Female"});
		comboSex.setText("Male");
		comboSex.setLayoutData(gridData);
		comboSex.addModifyListener(this);

		new Label(composite, SWT.NONE).setText("Phone:");
		textPhone = new Text(composite, SWT.BORDER);
		textPhone.setLayoutData(gridData);
		textPhone.addModifyListener(this);

		new Label(composite, SWT.NONE).setText("Address:");
		textAddress = new Text(composite, SWT.BORDER);
		textAddress.setLayoutData(gridData);
		textAddress.addModifyListener(this);
		setControl(composite);		
		setPageComplete(false);

		
	}
	public void modifyText(ModifyEvent e) {
		if (textName.getText().trim().length() == 0) {
			setMessage("Attention:Name must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		if (textAge.getText().trim().length() == 0) {
			setMessage("Attention:Age must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		if (comboSex.getText().trim().length() == 0) {
			setMessage("Attention:Sex must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		if (textPhone.getText().trim().length() == 0) {
			setMessage("Attention:Phone must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		if (textAddress.getText().trim().length() == 0) {
			setMessage("Attention:Address must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		setMessage(null);
		savePatientInfo(); // �������ʾͨ������֤������������Ӧ�����ݵ�һ��patient������
		setPageComplete(true);//��һ�����Ҫ�ģ���Ȼ��ȫ��д��ȷҲ�ǲ���next�ģ�
	}
	public void savePatientInfo() {

	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
