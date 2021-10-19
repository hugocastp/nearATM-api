package com.academy.ibm.nearatm.data;

import java.util.ArrayList;
import java.io.IOException;

public interface ISucursalService<T, M> {

	public T get(M searchCriteria);

	default public ArrayList<T> getAll() {
		return null;
	}
	public void setObjectsFromApi() throws IOException;

}
