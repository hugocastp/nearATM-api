package com.academy.ibm.nearatm.model;

import lombok.Data;

@Data
public class Sucursal {
	private String sucNumber;
	private String sucType;
	private Address sucAddress;
	private double sucLatitude;
	private double sucLongitude;

	public Sucursal(String sucNumber, String sucType, Address sucAddress, double sucLatitude, double sucLongitude) {
		this.sucNumber = sucNumber;
		this.sucType = sucType;
		this.sucAddress = sucAddress;
		this.sucLatitude = sucLatitude;
		this.sucLongitude = sucLongitude;
	}

	public Sucursal(){}
}
