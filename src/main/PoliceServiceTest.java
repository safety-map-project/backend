package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Impl.PoliceDaoImpl;
import model.Police;
import util.APIUtil.PoliceAPI;

public class PoliceServiceTest {

	public static void main(String[] args) {
		PoliceDaoImpl dao = new PoliceDaoImpl();
		PoliceAPI police = new PoliceAPI();
		List<Police> policeList;

		try {
			policeList = police.insertPoliceList(police.getPoliceAPI());
			int result = dao.insertPolice(policeList);

			System.out.println("총 파출소 수: " + policeList.size());
			System.out.println("저장된 파출소 수: " + result);

			if (result == policeList.size()) {
				System.out.println("파출소 데이터 저장 완료");
			} else {
				System.out.println("저장 중 일부 오류 발생");
			}
		} catch (IOException | InterruptedException | SQLException e) {
			System.out.println("저장 중 오류 발생");
			e.printStackTrace();
		}
	}
}