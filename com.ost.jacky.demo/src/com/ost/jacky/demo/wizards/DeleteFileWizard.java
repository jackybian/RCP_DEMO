package com.ost.jacky.demo.wizards;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.ost.jacky.demo.viewers.viewerContentProvider.ViewLabelProvider;

public class DeleteFileWizard extends Wizard{
	
	private DeleteFileWizardPage deleteFileWizardPage;
	
    private ImageDescriptor createImageDescriptor(String fileName) {
        Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
        URL url = FileLocator.find(bundle, new Path(fileName), null);
        return ImageDescriptor.createFromURL(url);
    }
	
	@Override
	public void addPages() {
		System.out.println("addPages");
		getShell().setImage(new Image(null, createImageDescriptor("/icons/small/add.gif").getImageData()));
		getShell().setText("Add Patient Information");
		deleteFileWizardPage = new DeleteFileWizardPage();
		addPage(deleteFileWizardPage);
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		System.out.println("performFinish");
		return false;
	}

}
