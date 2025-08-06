package httpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.Impl.CrimeDaoImpl;
import model.Crime;
import model.CrimeAvg;
import util.HandlerUtil;

public class CrimeAvgHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		HandlerUtil.optionsEquals(exchange);

		CrimeDaoImpl crimeDao = new CrimeDaoImpl();

		try {
			List<Crime> crimeList = crimeDao.listCrime();
			CrimeAvg crimeAvg = crimeDao.calculateCrimeAverage(crimeList);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(crimeAvg);

			HandlerUtil.sendResponse(exchange, json);

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
}