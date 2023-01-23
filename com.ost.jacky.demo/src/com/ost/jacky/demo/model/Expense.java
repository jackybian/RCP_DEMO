package com.ost.jacky.demo.model;

import java.util.Date;


public class Expense {
	
	private Long id;//id��ţ�bigint
	private String expenseDesc;//����˵��
	private String expenseName;//��������
	private float unitPrice;//����
	private int number;//����
	private float expenseSum;//ʵ�ʵķ���
	private Date takeDate;//���÷���ʱ��
	private Patient patient;//����
	private Doctor doctor;//ҽ��
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getExpenseDesc() {
		return expenseDesc;
	}
	public void setExpenseDesc(String expenseDesc) {
		this.expenseDesc = expenseDesc;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getExpenseSum() {
		return expenseSum;
	}
	public void setExpenseSum(float expenseSum) {
		this.expenseSum = expenseSum;
	}
	public Date getTakeDate() {
		return takeDate;
	}
	public void setTakeDate(Date takeDate) {
		this.takeDate = takeDate;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
