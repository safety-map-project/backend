package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Impl.PoliceDaoImpl;
import model.Police;
import util.APIUtil.PoliceAPI;

public class PoliceServiceTest {

	public static void main(String[] args) {
		try {
			// API에서 파출소 리스트 가져오기
			PoliceAPI api = new PoliceAPI();
			List<Police> policeList = api.insertPoliceList();
			for (Police p : policeList) {
				System.out.println(p);
			}

			// DB에 저장하기
//			PoliceDaoImpl dao = new PoliceDaoImpl();
//			dao.insertPolice(policeList);

//			System.out.println("파출소 데이터 저장 완료!");

		} catch (Exception e) {
			System.out.println("오류 발생: " + e.getMessage());
		}
	}
}