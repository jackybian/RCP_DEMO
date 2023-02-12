package com.ost.jacky.demo.editors.editorInput;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class FileEditorInput implements IEditorInput{
	
	private String fileName;
	
	private File file;

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return fileName;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public File getFile() {
		return this.file;
	}
	
	public String getFileName() {
		return this.fileName;
	}
}
