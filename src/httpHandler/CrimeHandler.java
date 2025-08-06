package httpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Crime;
import service.CrimeService;
import service.Impl.CrimeServiceImpl;
import util.HandlerUtil;

public class CrimeHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		HandlerUtil.optionsEquals(exchange);

		CrimeService crimeService = new CrimeServiceImpl();

		try {
			
			List<Crime> jsonCirmeList = new ArrayList<Crime>();
			for(Crime crime : crimeService.listCrime()) {
				jsonCirmeList.add(
					new Crime(crime.getCrimeId(), crime.getCrimeYear(), crime.getCrimeType(),
							crime.getCrimeCount(), crime.getRegionId(), crime.getRegion())
				);
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(jsonCirmeList);
		
			// json 보내는 메서드
			HandlerUtil.sendResponse(exchange, json);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}


	}

}


