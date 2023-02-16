package com.ost.jacky.demo.wizards;

import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.ost.jacky.demo.editors.PatientInfoEditor;
import com.ost.jacky.demo.util.QueryCondition;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewLabelProvider;

public class DeleteFileWizard extends Wizard{
	
	private DeleteFilePreQueryWizardPage deleteFileWizardPage;
	private DeleteFileContainsQueryWizardPage deleteFileContainsQueryWizardPage;
	private DeleteFilePostQueryWizardPage deleteFilePostQueryWizardPage;
	
	private QueryCondition queryCondition ;
	
	private List<String> list;
	
    private ImageDescriptor createImageDescriptor(String fileName) {
        Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
        URL url = FileLocator.find(bundle, new Path(fileName), null);
        return ImageDescriptor.createFromURL(url);
    }
	
	public QueryCondition getQueryCondition() {
		return queryCondition;
	}
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public void setQueryCondition(QueryCondition queryCondition) {
		this.queryCondition = queryCondition;
	}

	@Override
	public void addPages() {
		System.out.println("addPages");
		getShell().setImage(new Image(null, createImageDescriptor("/icons/small/add.gif").getImageData()));
		getShell().setText("Delete File");
		queryCondition = new QueryCondition();
		deleteFileWizardPage = new DeleteFilePreQueryWizardPage(queryCondition);
		deleteFileContainsQueryWizardPage = new DeleteFileContainsQueryWizardPage(queryCondition);
		deleteFilePostQueryWizardPage = new DeleteFilePostQueryWizardPage(queryCondition);
		addPage(deleteFileWizardPage);
		addPage(deleteFileContainsQueryWizardPage);
		addPage(deleteFilePostQueryWizardPage);
	}

	@Override
	public boolean performFinish() {
		System.out.println("performFinish");
		System.out.println(queryCondition.getPreConditions());
		System.out.println("================================");
		System.out.println(queryCondition.getContainConditions());
		System.out.println("================================");
		System.out.println(queryCondition.getPostConditions());
		list.clear();
		list.add("1");
		list.add("2");
		list.add("3");
		return true;
	}

}
