package com.academy.ibm.nearatm.api;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.academy.ibm.nearatm.data.ISucursalService;
import com.academy.ibm.nearatm.geocode.GeocodeService;
import com.academy.ibm.nearatm.model.Sucursal;

@RestController
public class SucursalController {

	@Autowired
	@Qualifier("geocodeService")
	private GeocodeService geocodeService;

	@Autowired
	private ISucursalService<Sucursal, LatLng> sucService;

	// endpoint para encontrar la sucursal mas cercana dadas
	// la latitud y longitud del usuario
	@RequestMapping(path = "/sucursal/{latitud}/{longitud}", method = RequestMethod.GET)
	public Sucursal getSucursal(@PathVariable double latitud, @PathVariable double longitud) {
		LatLng geocode = new LatLng(latitud, longitud);
		try{
		sucService.setObjectsFromApi();
		Sucursal suc = sucService.get(geocode);
		return suc;
		}catch(IOException e){
			e.printStackTrace();
		}
		return new Sucursal();	
	}

	// endpoint para una solicitud por defecto sin latitud ni longitud (Muestra
	// todas las sucursales)

	@RequestMapping(path = "/sucursal", method = RequestMethod.GET)
	public ArrayList<Sucursal> getSucursales() {
		try{
			sucService.setObjectsFromApi();
		return sucService.getAll();
		}catch(IOException e){
			e.printStackTrace();
		}
		return new ArrayList<Sucursal>();
	}

}
