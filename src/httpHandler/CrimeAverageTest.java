package httpHandler;

import java.sql.SQLException;
import java.util.List;

import dao.Impl.CrimeDaoImpl;
import model.Crime;
import model.CrimeAvg;

public class CrimeAverageTest {

	public static void main(String[] args) {
		try {
			CrimeDaoImpl dao = new CrimeDaoImpl();

			// DB에서 데이터 가져오기
			List<Crime> crimeList = dao.listCrime();


			CrimeAvg avg = dao.calculateCrimeAverage(crimeList);
			System.out.println("서울 평균 범죄 건수: " + avg.getSeoulCrimeAvg());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
