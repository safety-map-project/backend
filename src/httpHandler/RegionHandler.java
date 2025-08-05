package httpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
    	HandlerUtil.optionsEquals(exchange);
    	
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CoordService coordService = new CoordServiceImpl();
        
        try {
        	
        	String query = exchange.getRequestURI().getQuery().trim();
        	System.out.println(query);
        	String guName = null;
        	if(query.contains("시")) {
            	int siIdx = query.indexOf("시");
            	guName = query.substring(siIdx+1).trim();
        	} else {
        		int eIdx = query.indexOf("=");
        		guName = query.substring(eIdx+1).trim();
        	}
//        	System.out.println("guname: "+guName);
        	
        	List<Coord> coordList = coordService.guCoordsList(guName);
        	
        	List<double[]> coordPair = new ArrayList<double[]>();
        	
        	for(Coord coords : coordList) {
        		coordPair.add(makeCoordPairArr(coords.getLat(), coords.getLog()));
        	}
//        	
        	String json = gson.toJson(coordPair);
        	HandlerUtil.sendResponse(exchange, json);
        	
        	
        } catch(SQLException sqle) {
        	sqle.printStackTrace();
        }
    
    }
    
    public double[] makeCoordPairArr(double lat, double log) {
    	double[] onePair = {lat, log};
    	return onePair;
    }
    
   
}
    

	

