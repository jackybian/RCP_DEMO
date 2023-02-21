package com.ost.jacky.demo;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.ost.jacky.demo.editors.editorInput.FileEditorInput;
import com.ost.jacky.demo.util.PluginUtil;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewContentProvider;
import com.ost.jacky.demo.viewers.viewerContentProvider.ViewLabelProvider;

public class View extends ViewPart {
	public static final String ID = "com.ost.jacky.demo.view";

	
	private TreeViewer treeViewer;

	@Override
	public void createPartControl(Composite parent) {
	    treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
	    // 设置内容提供其和标签提供器
	    treeViewer.setContentProvider(new ViewContentProvider());
	    treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(
                new ViewLabelProvider(createImageDescriptor())));
	    // 读入数据
	    treeViewer.setInput(File.listRoots());
	    // 自定义的方法
	    // 设置视图的工具栏
	    //etViewToolBar();
	    // 自定义的方法，实现双击打开相应的编辑器的功能
	    hookDoubleClickAction();
	}
	
    private ImageDescriptor createImageDescriptor() {
        Bundle bundle = FrameworkUtil.getBundle(ViewLabelProvider.class);
        URL url = FileLocator.find(bundle, new Path("icons/folder.ico"), null);
        return ImageDescriptor.createFromURL(url);
    }

	  // 这个方法实际上就是给treeviewer添加了一个处理双击事件的监听器
	  private void hookDoubleClickAction() {
	    treeViewer.addDoubleClickListener(new IDoubleClickListener() {
	      @Override
	      public void doubleClick(DoubleClickEvent event) {
	        ISelection selection = treeViewer.getSelection();
	        // 得到选中的项，注意方法是将得到的选项转换成 IStructuredSelection，再调用 getFirstElement 方法
	        Object object = ((IStructuredSelection) selection).getFirstElement();
	        
	        // 再将对象转为实际的树节点对象
	        File element = (File) object;
	        if(element.isDirectory()) {
	        	return;
	        }
	        System.out.println(element.getName());
	        if (!element.getName().endsWith("ser")) {
	        	return;
	        }
	        FileEditorInput fileEditorInput = new FileEditorInput();
	        fileEditorInput.setFile(element);
	        fileEditorInput.setFileName(element.getAbsolutePath());
	        IWorkbenchPage workbenchPage = getViewSite().getPage();
	        String editorID = null;
	        // 其实这边是指定的类路径，要与viewer里面的ID，进行区分开来
	        editorID = PluginUtil.PatientInfoEditor_ID;
	        IEditorPart editorPart = workbenchPage.findEditor(fileEditorInput);
	        if(editorPart != null){//已经打开了所需的编辑器
	          workbenchPage.bringToTop(editorPart);
	        }else {//没有打开就打开来
	          try {
	            editorPart = workbenchPage.openEditor(fileEditorInput, editorID);
	          } catch (Exception e) {
	            e.printStackTrace();
	          }
	        }
	      }
	    });
	  }
	
	
	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}
	
}