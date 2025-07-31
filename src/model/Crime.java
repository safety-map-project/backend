package model;

import java.io.Serializable;

public class Crime implements Serializable {

	private static final long serialVersionUID = 685464886485441L;

	private int crimeId; // 범죄발생지 지역별 아이디
	private int crimeYear; // 발생년도
	private int regionId; // 지역아이디
	private String crimeType; // 범죄타입
	private int crimeCount; // 범죄건수
	private String region;

	public Crime() {
	}

	public Crime(int crimeId, int crimeYear, int regionId, String crimeType, int crimeCount, String region) {
		this.crimeId = crimeId;
		this.crimeYear = crimeYear;
		this.regionId = regionId;
		this.crimeType = crimeType;
		this.crimeCount = crimeCount;
		this.region = region;
	}
	
	public Crime(int crimeId, int crimeYear, int regionId, String crimeType, int crimeCount) {
		this.crimeId = crimeId;
		this.crimeYear = crimeYear;
		this.regionId = regionId;
		this.crimeType = crimeType;
		this.crimeCount = crimeCount;
	}

	public int getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(int crimeId) {
		this.crimeId = crimeId;
	}

	public int getCrimeYear() {
		return crimeYear;
	}

	public void setCrimeYear(int crimeYear) {
		this.crimeYear = crimeYear;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getCrimeType() {
		return crimeType;
	}

	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}

	public int getCrimeCount() {
		return crimeCount;
	}

	public void setCrimeCount(int crimeCount) {
		this.crimeCount = crimeCount;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Crime [crimeId=" + crimeId + ", crimeYear=" + crimeYear + ", regionId=" + regionId + ", crimeType="
				+ crimeType + ", crimeCount=" + crimeCount + ", region=" + region + "]";
	}

}
