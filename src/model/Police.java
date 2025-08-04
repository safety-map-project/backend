package model;

import java.io.Serializable;

public class Police implements Serializable {

	private static final long serialVersionUID = 5646545165415645L;

	private int policeId; // 파출소아이디
	private String location; // 위치(주소)
	private String police_address; // 을지 지구대
	private int regionId; // 지역아이디
	private double lat; // 위도
	private double log; // 경도

	public Police() {
	}

	public Police(int policeId, String location, String police_address, int regionId, double lat, double log) {
		super();
		this.policeId = policeId;
		this.location = location;
		this.police_address = police_address;
		this.regionId = regionId;
		this.lat = lat;
		this.log = log;
	}

	public String getPolice_address() {
		return police_address;
	}

	public void setPolice_address(String police_address) {
		this.police_address = police_address;
	}

	public int getPoliceId() {
		return policeId;
	}

	public void setPoliceId(int policeId) {
		this.policeId = policeId;
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
		return "Police [policeId=" + policeId + ", location=" + location + ", police_address=" + police_address
				+ ", regionId=" + regionId + ", lat=" + lat + ", log=" + log + "]";
	}

}
