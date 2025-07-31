package model;

import java.io.Serializable;

public class Region implements Serializable {

	private static final long serialVersionUID = 562655665565263263L;

	private String regionId; // 지역아이디
	private String gu; // 구
//	private int startIat; // 시작위도
//	private int startLog; // 시작경도
//	private int endLat; // 끝위도
//	private int endLog; // 끝경도

	public Region() {
	}

	public Region(String regionId, String gu) {
		this.regionId = regionId;
		this.gu = gu;
//		this.startIat = startIat;
//		this.startLog = startLog;
//		this.endLat = endLat;
//		this.endLog = endLog;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	
	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

//	public int getStartIat() {
//		return startIat;
//	}
//
//	public void setStartIat(int startIat) {
//		this.startIat = startIat;
//	}
//
//	public int getStartLog() {
//		return startLog;
//	}
//
//	public void setStartLog(int startLog) {
//		this.startLog = startLog;
//	}
//
//	public int getEndLat() {
//		return endLat;
//	}
//
//	public void setEndLat(int endLat) {
//		this.endLat = endLat;
//	}
//
//	public int getEndLog() {
//		return endLog;
//	}
//
//	public void setEndLog(int endLog) {
//		this.endLog = endLog;
//	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId +  "gu=" + gu + "]";
	}

}
