package model;

import java.io.Serializable;

public class Cctv implements Serializable {

	private static final long serialVersionUID = 56451654645641515L;

	private int cctvId; // cctv아이디
	private String location; // 위치(주소)
	private int regionId; // 지역아이디
	private double lat; // 위도
	private double log; // 경도

	public Cctv() {
	}

	public Cctv(int cctvId, String location, int regionId, double lat, double log) {
		this.cctvId = cctvId;
		this.location = location;
		this.regionId = regionId;
		this.lat = lat;
		this.log = log;
	}

	public int getCctvId() {
		return cctvId;
	}

	public void setCctvId(int cctvId) {
		this.cctvId = cctvId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
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

	@Override
	public String toString() {
		return "Cctv [cctvId=" + cctvId + ", location=" + location + ", regionId=" + regionId + ", lat=" + lat
				+ ", log=" + log + "]";
	}

}
