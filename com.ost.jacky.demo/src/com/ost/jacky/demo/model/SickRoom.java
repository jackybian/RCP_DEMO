package com.ost.jacky.demo.model;


public class SickRoom {

	private Long id;//id��ţ�bigint
	private int sickRoomNo;//������ţ�int
	private Department department = new Department();//���ң��������ڵĿ��ң�bigint

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSickRoomNo() {
		return sickRoomNo;
	}

	public void setSickRoomNo(int sickRoomNo) {
		this.sickRoomNo = sickRoomNo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
