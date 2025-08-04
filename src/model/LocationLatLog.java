package model;

import java.io.Serializable;

public class LocationLatLog implements Serializable {

	private static final long serialVersionUID = 32432141242314L;

	private String 주소;
	private double Lat;
	private double Log;

	public LocationLatLog() {
		// TODO Auto-generated constructor stub
	}

	public LocationLatLog(String location, double lat, double log) {
		super();
		주소 = location;
		Lat = lat;
		Log = log;
	}

	public String getLocation() {
		return 주소;
	}

	public void setLocation(String location) {
		주소 = location;
	}

	public double getLat() {
		return Lat;
	}

	public void setLat(double lat) {
		Lat = lat;
	}

	public double getLog() {
		return Log;
	}

	public void setLog(double log) {
		Log = log;
	}

	@Override
	public String toString() {
		return "Location [Location=" + 주소 + ", Lat=" + Lat + ", Log=" + Log + "]";
	}

}
