package main;

import java.io.IOException;

import util.APIUtil.PoliceAPI;

public class PoliceServiceTest {

	public static void main(String[] args) {

		PoliceAPI policeAPI = new PoliceAPI();

		try {
			policeAPI.getPoliceAPI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
