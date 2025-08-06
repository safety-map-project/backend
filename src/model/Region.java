package model;

import java.io.Serializable;

public class Region implements Serializable {

	private static final long serialVersionUID = 562655665565263263L;

	private int regionId; // 지역아이디
	private String gu; // 구
	private String si; // 광역시

	public Region() {
	}


	public Region(int regionId, String gu, String si) {
		super();
		this.regionId = regionId;
		this.gu = gu;
		this.si = si;
	}


	public int getRegionId() {
		return regionId;
	}


	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}


	public String getGu() {
		return gu;
	}


	public void setGu(String gu) {
		this.gu = gu;
	}


	public String getSi() {
		return si;
	}


	public void setSi(String si) {
		this.si = si;
	}


	@Override
	public String toString() {
		return "Region [regionId=" + regionId + "si" + si + ", gu=" + gu + "]";
	}

}
