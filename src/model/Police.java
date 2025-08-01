package model;

import java.io.Serializable;

public class Police implements Serializable {

	private static final long serialVersionUID = 5646545165415645L;

	private int policeId; // 파출소아이디
	private String name;
	private String location; // 위치(주소)
	private String regionId; // 지역아이디
	private double lat; // 위도
	private double log; // 경도

	public Police() {};

	public Police(int policeId, String name, String location, String regionId, double lat, double log) {
		super();
		this.policeId = policeId;
		this.name = name;
		this.location = location;
		this.regionId = regionId;
		this.lat = lat;
		this.log = log;
	};

	public int getPoliceId() {
		return policeId;
	}

	public void setPoliceId(int policeId) {
		this.policeId = policeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String string) {
		this.regionId = string;
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
}
