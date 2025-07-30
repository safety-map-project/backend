package model;

import java.io.Serializable;

public class Police implements Serializable {

	private static final long serialVersionUID = 5646545165415645L;

	private int policeId; // 파출소아이디
	private String location; // 위치(주소)
	private int regionId; // 지역아이디
	private int lat; // 위도
	private int log; // 경도

	public Police() {
	}

	public Police(int policeId, String location, int regionId, int lat, int log) {
		this.policeId = policeId;
		this.location = location;
		this.regionId = regionId;
		this.lat = lat;
		this.log = log;
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

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLog() {
		return log;
	}

	public void setLog(int log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "Police [policeId=" + policeId + ", location=" + location + ", regionId=" + regionId + ", lat=" + lat
				+ ", log=" + log + "]";
	}

}
