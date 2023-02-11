package com.ost.jacky.demo;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.ost.jacky.demo.util.PluginUtil;


public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.addView(PluginUtil.NavigatorView_ID, IPageLayout.LEFT, 0.3f, editorArea);
		IFolderLayout rightbottom = layout.createFolder("rightbottom", IPageLayout.BOTTOM, 0.7f, editorArea);
		rightbottom.addView(PluginUtil.PatientInfoView_ID);
	}

}
