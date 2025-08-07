package httpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Region;
import service.RegionService;
import service.Impl.RegionServiceImpl;
import util.HandlerUtil;

public class RegionSiGuHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		if(exchange.getRequestMethod().equals("GET")) {
			
			System.out.println(exchange.getRequestURI());
			
			RegionService regionService = new RegionServiceImpl();
			
			try {
				
				List<Region> regionList = new ArrayList<Region>();
				for(Region region : regionService.listRegion()) {
					
					regionList.add(
							new Region(region.getRegionId(), region.getGu(), region.getSi())
							);
					
				}
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(regionList);
				
				HandlerUtil.sendResponse(exchange, json);
				
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		

	}

}
