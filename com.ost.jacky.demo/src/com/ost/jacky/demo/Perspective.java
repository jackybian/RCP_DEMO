package com.ost.jacky.demo;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.ost.jacky.demo.util.PluginUtil;


public class Perspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
//		String editorArea = layout.getEditorArea();//Returns the special identifier for the editor area in this page layout
//		layout.addView(PluginUtil.NavigatorView_ID, IPageLayout.LEFT, 0.3f, editorArea);
//		IFolderLayout leftbottom = layout.createFolder("left", IPageLayout.BOTTOM, 0.4f, PluginUtil.NavigatorView_ID);
//		leftbottom.addView(PluginUtil.SearchView_ID);
//		IFolderLayout rightbottom = layout.createFolder("rightbottom", IPageLayout.BOTTOM, 0.7f, editorArea);
//		rightbottom.addView(PluginUtil.PatientInfoView_ID);
//		rightbottom.addView(PluginUtil.ExpenseInfoView_ID);
	}

}
