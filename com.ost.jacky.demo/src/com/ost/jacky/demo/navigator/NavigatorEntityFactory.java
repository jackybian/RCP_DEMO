package com.ost.jacky.demo.navigator;

import java.util.ArrayList;
import java.util.List;

import com.ost.jacky.demo.editors.editorInput.PatientInfoEditorInput;

public class NavigatorEntityFactory {
	  public static Object setNavigatorEntity() {
		    /**
		     * 这里涉及到一个统一化的问题，有些String在其他的地方引用了，不便于修改！
		     * 保存起来？！
		     */

		    // Level one 一级节点
		    NavigatorEntityElement root1 = new NavigatorEntityElement(0, "Hospital Management");
		    NavigatorEntityElement root2 = new NavigatorEntityElement(0, "Expense Management");
		    NavigatorEntityElement root3 = new NavigatorEntityElement(0, "Information Search");

		    // level two 二级节点
		    NavigatorEntityElement element1 = new NavigatorEntityElement(1, "Patient Information");
		    NavigatorEntityElement element2 = new NavigatorEntityElement(1, "Diagnose Information");
		    NavigatorEntityElement element3 = new NavigatorEntityElement(1, "Expense Information-1");

		    // 将下级节点添加到上级
		    root1.addChildren(element1);
		    root1.addChildren(element2);
		    root2.addChildren(element3);

		    // 设置编辑器
		    element1.setEditorInput(new PatientInfoEditorInput());
//		    element2.setEditorInput(new DiagnoseInfoEditorInput());
//		    element3.setEditorInput(new ExpenseInfoEditorInput());

		    // 要返回的list
		    List list = new ArrayList();
		    list.add(root1);
		    list.add(root2);
		    list.add(root3);
		    return list;
		  }
}
