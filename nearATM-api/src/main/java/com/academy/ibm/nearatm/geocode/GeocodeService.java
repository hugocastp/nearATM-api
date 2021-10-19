package com.academy.ibm.nearatm.geocode;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.academy.ibm.nearatm.model.Address;
import com.academy.ibm.nearatm.model.Sucursal;

import org.springframework.stereotype.Component;

import lombok.Data;

//Servicio para localizar la latitud y longitud de una sucursal 
//usando el servicio de geocoding de Google.

@Component
@Data
public class GeocodeService {

	private static final Logger LOG = Logger.getLogger(GeocodeService.class.getName());
	private GeoApiContext context;
	private String apikey;

	private void initializeGeoApiContext() {	
		context = new GeoApiContext();
		context.setApiKey(apikey);
	}

	//retorna el geocode de la sucursal
	public LatLng getGeocode(Sucursal suc) {
		initializeGeoApiContext();
		GeocodingResult[] results = null;
		LatLng geocode = null;
		try {
			results = GeocodingApi.geocode(context, getFormattedAddress(suc)).await();
			geocode = results[0].geometry.location;
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Exception in invoking Google geocoding API :", e.getCause());
		}
		return geocode;
	}

	//Direccion formateada

	private String getFormattedAddress(Sucursal suc) {
		Address address = suc.getSucAddress();
		StringBuilder formattedAddress = new StringBuilder();
		if (Objects.nonNull(suc.getSucNumber())) {
			formattedAddress.append(suc.getSucNumber()).append(",");
		}
		if (Objects.nonNull(suc.getSucType())) {
			formattedAddress.append(suc.getSucType()).append(",");
		}
		if (Objects.nonNull(address.getAddressLine1())) {
			formattedAddress.append(address.getAddressLine1()).append(",");
		}
		if (Objects.nonNull(address.getAddressLine2())) {
			formattedAddress.append(address.getAddressLine2()).append(",");
		}
		LOG.log(Level.INFO, "Evaluando el geocode para la dirección :", formattedAddress.toString());
		return formattedAddress.toString();
	}


}
