package com.academy.ibm.nearatm.model;

import lombok.Data;

@Data
public class Address {
	private String addressLine1;
	private String addressLine2;

	public Address(){}

	public Address(String addressLine1, String addressLine2) {
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}

}
