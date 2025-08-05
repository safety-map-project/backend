package httpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Police;
import service.PoliceService;
import service.Impl.PoliceServiceImpl;
import util.HandlerUtil;

public class PoliceHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		HandlerUtil.optionsEquals(exchange);
		
		PoliceService policeService = new PoliceServiceImpl();
		
		try {
			
			List<Police> jsonPolieList = new ArrayList<Police>();
			for(Police police : policeService.listPolice()) {
				jsonPolieList.add(
					new Police(police.getPoliceId(), police.getLocation(), police.getPolice_address(),
							police.getRegionId(), police.getLat(), police.getLog(), police.getName())
			);
		}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(jsonPolieList);
			
			HandlerUtil.sendResponse(exchange, json);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		
	}

}
