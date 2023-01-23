package com.ost.jacky.demo.model;


public class SickBed {
	private Long id;//id��ţ�bigint
	private int sickBedNo;//������ţ�int
	private SickRoom sickRoom = new SickRoom();//�������������ڵĲ���

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSickBedNo() {
		return sickBedNo;
	}

	public void setSickBedNo(int sickBedNo) {
		this.sickBedNo = sickBedNo;
	}

	public SickRoom getSickRoom() {
		return sickRoom;
	}

	public void setSickRoom(SickRoom sickRoom) {
		this.sickRoom = sickRoom;
	}

}
