package model;

import java.io.Serializable;

public class Coord implements Serializable{
	
	private static final long serialVersionUID = 3952803958230L;
	
	private int coordId;
	private double lat;
	private double log;
	private String regionId;
	
	
	public Coord(int coordId, double lat, double log, String regionId) {
		super();
		this.coordId = coordId;
		this.lat = lat;
		this.log = log;
		this.regionId = regionId;
	}


	public int getCoordId() {
		return coordId;
	}


	public void setCoordId(int coordId) {
		this.coordId = coordId;
	}


	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}


	public double getLog() {
		return log;
	}


	public void setLog(double log) {
		this.log = log;
	}


	public String getRegionId() {
		return regionId;
	}


	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}


	@Override
	public String toString() {
		return "Coord [coordId=" + coordId + ", lat=" + lat + ", log=" + log + ", regionId=" + regionId + "]";
	}
	
	
	
	
	
} // class
