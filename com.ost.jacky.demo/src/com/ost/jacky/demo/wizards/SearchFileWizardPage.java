package com.ost.jacky.demo.wizards;

import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SearchFileWizardPage extends WizardPage implements ModifyListener{
	private Text searchCondition;
	
	private List<String> searchList;

	protected SearchFileWizardPage(List<String> searchList) {
		super("");
		setTitle("Search File Wizard");
		setMessage("Attention:Please input the following file name!", IMessageProvider.INFORMATION);	
		this.searchList = searchList;
	}
	
	protected SearchFileWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		searchCondition = new Text(composite,  SWT.BORDER);
		searchCondition.addModifyListener(this);
		setControl(composite);		
		setPageComplete(false);
	}

	@Override
	public void modifyText(ModifyEvent e) {	
		searchList.add(searchCondition.getText());
		setPageComplete(true);
	}

}
