package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CrimeAvg implements Serializable {

	private static final long serialVersionUID = 314134134132412L;

	private double seoulCrimeAvg;
	private double busanCrimeAvg;
	private double daeguCrimeAvg;
	private double incheonCrimeAvg;
	private double gwangjuCrimeAvg;
	private double daejeonCrimeAvg;
	private double ulsanCrimeAvg;

	public CrimeAvg() {
		// TODO Auto-generated constructor stub
	}

	public CrimeAvg(double seoulCrimeAvg, double busanCrimeAvg, double daeguCrimeAvg, double incheonCrimeAvg,
			double gwangjuCrimeAvg, double daejeonCrimeAvg, double ulsanCrimeAvg) {
		super();
		this.seoulCrimeAvg = seoulCrimeAvg;
		this.busanCrimeAvg = busanCrimeAvg;
		this.daeguCrimeAvg = daeguCrimeAvg;
		this.incheonCrimeAvg = incheonCrimeAvg;
		this.gwangjuCrimeAvg = gwangjuCrimeAvg;
		this.daejeonCrimeAvg = daejeonCrimeAvg;
		this.ulsanCrimeAvg = ulsanCrimeAvg;
	}

	public double getSeoulCrimeAvg() {
		return seoulCrimeAvg;
	}

	public void setSeoulCrimeAvg(double seoulCrimeAvg) {
		this.seoulCrimeAvg = seoulCrimeAvg;
	}

	public double getBusanCrimeAvg() {
		return busanCrimeAvg;
	}

	public void setBusanCrimeAvg(double busanCrimeAvg) {
		this.busanCrimeAvg = busanCrimeAvg;
	}

	public double getDaeguCrimeAvg() {
		return daeguCrimeAvg;
	}

	public void setDaeguCrimeAvg(double daeguCrimeAvg) {
		this.daeguCrimeAvg = daeguCrimeAvg;
	}

	public double getIncheonCrimeAvg() {
		return incheonCrimeAvg;
	}

	public void setIncheonCrimeAvg(double incheonCrimeAvg) {
		this.incheonCrimeAvg = incheonCrimeAvg;
	}

	public double getGwangjuCrimeAvg() {
		return gwangjuCrimeAvg;
	}

	public void setGwangjuCrimeAvg(double gwangjuCrimeAvg) {
		this.gwangjuCrimeAvg = gwangjuCrimeAvg;
	}

	public double getDaejeonCrimeAvg() {
		return daejeonCrimeAvg;
	}

	public void setDaejeonCrimeAvg(double daejeonCrimeAvg) {
		this.daejeonCrimeAvg = daejeonCrimeAvg;
	}

	public double getUlsanCrimeAvg() {
		return ulsanCrimeAvg;
	}

	public void setUlsanCrimeAvg(double ulsanCrimeAvg) {
		this.ulsanCrimeAvg = ulsanCrimeAvg;
	}

	@Override
	public String toString() {
		return "CrimeAvg [seoulCrimeAvg=" + seoulCrimeAvg + ", busanCrimeAvg=" + busanCrimeAvg + ", daeguCrimeAvg="
				+ daeguCrimeAvg + ", incheonCrimeAvg=" + incheonCrimeAvg + ", gwangjuCrimeAvg=" + gwangjuCrimeAvg
				+ ", daejeonCrimeAvg=" + daejeonCrimeAvg + ", ulsanCrimeAvg=" + ulsanCrimeAvg + "]";
	}

}
