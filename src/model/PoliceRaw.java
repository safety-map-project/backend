package model;

import java.util.List;

public class PoliceRaw {
	int currentCount;
	List<Data> data;
	
	public PoliceRaw() {};

	public PoliceRaw(int currentCount, List<Data> data) {
		this.currentCount = currentCount;
		this.data = data;
	};

	public int getCurrentCount() {
		return currentCount;
	};

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	};

	public List<Data> getData() {
		return data;
	};

	public void setData(List<Data> data) {
		this.data = data;
	};
}
