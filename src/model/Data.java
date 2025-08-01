package model;

public class Data {
	String district;
	String village;
	String role;
	String region;
	int id;
	String phone;
	String address;
	
	Data() {};

	public Data(String district, String village, String role, String region, int id, String phone, String address) {
		super();
		this.district = district;
		this.village = village;
		this.role = role;
		this.region = region;
		this.id = id;
		this.phone = phone;
		this.address = address;
	};

	public String getDistrict() {
		return district;
	};

	public void setDistrict(String district) {
		this.district = district;
	};

	public String getVillage() {
		return village;
	};

	public void setVillage(String village) {
		this.village = village;
	};

	public String getRole() {
		return role;
	};

	public void setRole(String role) {
		this.role = role;
	};

	public String getRegion() {
		return region;
	};

	public void setRegion(String region) {
		this.region = region;
	};

	public int getId() {
		return id;
	};

	public void setId(int id) {
		this.id = id;
	};

	public String getPhone() {
		return phone;
	};

	public void setPhone(String phone) {
		this.phone = phone;
	};

	public String getAddress() {
		return address;
	};
	
	public void setAddress(String address) {
		this.address = address;
	};
}
