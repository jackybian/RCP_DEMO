package com.ost.jacky.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.ost.jacky.demo.model.Patient;


public class PatientDAO {
	public List<Patient> getPatientInfoList() {
		List<Patient> patientList = new ArrayList<Patient>();
		Patient patient1 = new Patient();
		patient1.setName("张三");
		patient1.setAge(21);
		Patient patient2 = new Patient();
		patient2.setName("李四");
		patient2.setAge(22);
		Patient patient3 = new Patient();
		patient3.setName("王五");
		patient3.setAge(23);
		patientList.add(patient1);
		patientList.add(patient2);
		patientList.add(patient3);
		return patientList;
	}
}
