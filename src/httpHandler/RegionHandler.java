package httpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Coord;
import service.CoordService;
import service.Impl.CoordServiceImpl;
import util.HandlerUtil;

public class RegionHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CoordService coordService = new CoordServiceImpl();
        
        try {
        	
        	String guName = exchange.getRequestURI().getQuery().trim();
        	List<Coord> coordList = coordService.guCoordsList(guName);
        	Map<Double, Double> coordPairMap = new HashMap<Double, Double>();
        	
        	for(Coord coord : coordList) {
        		coordPairMap.put(coord.getLat(), coord.getLog());
        	}
        	
        	String json = gson.toJson(coordPairMap);
        	HandlerUtil.sendResponse(exchange, json);
        	
        } catch(SQLException sqle) {
        	sqle.printStackTrace();
        }
    
    }
}
    

	

