package com.ost.jacky.demo.wizards;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.ost.jacky.demo.editors.FileEditor;
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
		List<String> afterPreList = new ArrayList<>();
		String preCondition = queryCondition.getPreConditions();
		
		List<String> afterContainList = new ArrayList<>();
		List<String> afterPostList = new ArrayList<>();
		Set<String> set = new HashSet<>();
		for (String s : list) {
			set.add(s);
		}
		if (null != queryCondition.getPreConditions()) {
		String[] preConditions = queryCondition.getPreConditions().split("\\n");
		if (null == preConditions || preConditions.length == 0) {
			return true;
		}

		for (int index = 0; index < preConditions.length; index++) {
			String value = preConditions[index];
			for (String s : list) {
				if (s.startsWith(value)) {
					set.remove(s);
				}
			}
		}
		}
		
		
		if (null != queryCondition.getContainConditions()) {
		String[] preConditions = queryCondition.getContainConditions().split("\\n");
		if (null == preConditions || preConditions.length == 0) {
			return true;
		}

		for (int index = 0; index < preConditions.length; index++) {
			String value = preConditions[index];
			for (String s : list) {
				if (s.contains(value)) {
					set.remove(s);
				}
			}
		}
		}
		
		if (null != queryCondition.getPostConditions()) {
		String[] postConditions = queryCondition.getPostConditions().split("\\n");
		if (null == postConditions || postConditions.length == 0) {
			return true;
		}
		for (int index = 0; index < postConditions.length; index++) {
			String value = postConditions[index];
			for (String s : list) {
				if (s.endsWith(value)) {
					set.remove(s);
				}
			}
		}
		}
		list.clear();
		list.addAll(set);
		return true;
	}
	}

}
