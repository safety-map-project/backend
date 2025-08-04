package model;

import java.io.Serializable;

public class Coord implements Serializable{
	
	private static final long serialVersionUID = 3952803958230L;
	
	private int coordId;
	private double lat;
	private double log;
	private int regionId;
	private String gu_name;
	
	
	
	public Coord(int coordId, double lat, double log, int regionId, String gu_name) {
		super();
		this.coordId = coordId;
		this.lat = lat;
		this.log = log;
		this.regionId = regionId;
		this.gu_name = gu_name;
	}

	public Coord() {}
	

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


	public int getRegionId() {
		return regionId;
	}


	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}


	public String getGu_name() {
		return gu_name;
	}


	public void setGu_name(String gu_name) {
		this.gu_name = gu_name;
	}


	@Override
	public String toString() {
		return "Coord [coordId=" + coordId + ", lat=" + lat + ", log=" + log + ", regionId=" + regionId + ", gu_name="
				+ gu_name + "]";
	}
	
	
} // class
