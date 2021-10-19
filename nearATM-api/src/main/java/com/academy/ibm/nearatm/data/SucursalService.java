package com.academy.ibm.nearatm.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.maps.model.LatLng;
import com.academy.ibm.nearatm.model.Sucursal;

import org.springframework.stereotype.Component;

import com.academy.ibm.nearatm.api.LocationsAPI;

@Component
public class SucursalService implements ISucursalService<Sucursal, LatLng> {

	private static final Logger LOG = Logger.getLogger(SucursalService.class.getName());

	private ArrayList<Sucursal> data;

	@Override
	public void setObjectsFromApi()  throws IOException{
		LocationsAPI apiloc = new LocationsAPI();
		ObjectMapper mapper = new ObjectMapper();
        String response = apiloc.getLocations();
        response = apiloc.jsonClean(response);

        JsonNode actualObj = mapper.readTree(response);
        ((ObjectNode)actualObj).remove(Arrays.asList("800", "500", "110", "400", "600", "900", "950"));
    
      this.data = apiloc.getObjectsFromJson((ObjectNode) actualObj);
	}

	// Obtener la sucursal mas cercana al geocode
	@Override
	public Sucursal get(LatLng geocode) {
		Sucursal nearestSuc = findNearest(geocode);
		return nearestSuc;
	}

	//Obtener todas las sucursales 
	@Override
	public ArrayList<Sucursal> getAll() {
		return data;
	}

	//Encuentra la sucursal mas cercana al geocode ingresado
	public Sucursal findNearest(LatLng geocode) {
		// latitud y longitud del usuario
		double lat1 = geocode.lat;
		double lon1 = geocode.lng;
		// guarda la ubicacion mas cercana hasta el momento
		double nearestDist = -1;
		// guarda la referencia a la sucursal mas cercana hasta el momento
		Sucursal nesarestSucursal = null;
		for (Sucursal suc : data) {
			// latitud y longitud de la sucursal a comparar
			double lat2 = suc.getSucLatitude();
			double lon2 = suc.getSucLongitude();
			// distancia a la sucursal en comparacion
			double dist = DistanceCalc.haversine(lat1, lon1, lat2, lon2);
			// Si la sucursal comparada esta mas cerca que la anterior sucursal 
			// o si es la primera sucursal
			if (dist < nearestDist || nearestDist == -1) {
				nesarestSucursal = suc;
				nearestDist = dist;
				LOG.log(Level.INFO, " Sucursal " + nesarestSucursal.getSucNumber() + " encontrada a " + nearestDist + " KM");
			}
		}
		return nesarestSucursal;
	}

}
