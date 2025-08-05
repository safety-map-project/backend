package model;

import java.io.Serializable;

public class Region implements Serializable {

	private static final long serialVersionUID = 562655665565263263L;

	private String regionId; // 지역아이디
	private String gu; // 구
	private String si; // 광역시

	public Region() {
	}

	public Region(String regionId, String gu) {
		this.regionId = regionId;
		this.gu = gu;
	}
	public Region(String regionId, String gu, String si) {
		this.regionId = regionId;
		this.gu = gu;
		this.si = si;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String string) {
		this.regionId = string;
	}

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
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
		return "Region [regionId=" + regionId + "si" + si + ", gu=" + gu + "]";
	}

}
