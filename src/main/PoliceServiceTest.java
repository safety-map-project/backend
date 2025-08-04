package main;

import java.io.IOException;
import java.util.List;

import model.Police;
import util.APIUtil.PoliceAPI;

public class PoliceServiceTest {

	public static void main(String[] args) {

		PoliceAPI api = new PoliceAPI();

		try {
			String jsonStr = api.getPoliceAPI();
			// 객체들을 넣은 리스트 받아옴
			List<Police> policeList = api.insertPoliceList(jsonStr);

			long count = policeList.stream().count();
			System.out.println("데이터 개수 : " + count);

			policeList.stream().forEach(element -> {
				System.out.println("파출소명 : " + element.getPolice_address());
				System.out.println("주소 : " + element.getLocation());
			});

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
