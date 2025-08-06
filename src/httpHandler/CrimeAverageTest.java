package httpHandler;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.Impl.CrimeDaoImpl;
import model.Crime;
import model.CrimeAvg;

public class CrimeAverageTest {

	public static void main(String[] args) {
		try {
			CrimeDaoImpl crimeDao = new CrimeDaoImpl();
			List<Crime> crimeList = crimeDao.listCrime();
			CrimeAvg crimeAvg = crimeDao.calculateCrimeAverage(crimeList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(crimeAvg);
			System.out.println(json);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
