package com.ost.jacky.demo.model;

import java.util.Date;


public class Diagnose {
	private Long id;//id��ţ�bigint
	private String illness;//��Ͻ��
	private String treatment;//��Ϸ���
	private Date diagnoseDate;//���ʱ��
	private Patient patient;//����
	private Doctor doctor;//ҽ��

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public Date getDiagnoseDate() {
		return diagnoseDate;
	}

	public void setDiagnoseDate(Date diagnoseDate) {
		this.diagnoseDate = diagnoseDate;
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
