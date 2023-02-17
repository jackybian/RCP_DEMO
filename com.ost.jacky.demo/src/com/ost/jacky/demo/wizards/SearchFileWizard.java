package com.ost.jacky.demo.wizards;

import java.util.List;

import org.eclipse.jface.wizard.Wizard;

public class SearchFileWizard extends Wizard{
	
	private SearchFileWizardPage searchFileWizardPage;
	
	private List<String> searchList;
	
	public SearchFileWizard(List<String> searchList) {
		this.searchList = searchList;
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	@Override
	public void addPages() {
		SearchFileWizardPage searchFileWizardPage = new SearchFileWizardPage(searchList);
		addPage(searchFileWizardPage);
	}
}
