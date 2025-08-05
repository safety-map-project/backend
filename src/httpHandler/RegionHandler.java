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
        	
        	settingStartandEnd(coordPair);
        	
        	
        	String json = gson.toJson(coordPair);
        	HandlerUtil.sendResponse(exchange, json);
        	
        	
        } catch(SQLException sqle) {
        	sqle.printStackTrace();
        }
    
    }
    
    public static double[] makeCoordPairArr(double lat, double log) {
    	double[] onePair = {lat, log};
    	return onePair;
    }
    
//  좌표들을 반시계방향으로 정렬 method (이렇게 해야 polygon 선이 안 꼬임)
    public static double[] sortCoordList(List<double[]>coordPair) {
    	
    	double[] centerCoord = getCenterCoord(coordPair);
    	double centerLat = centerCoord[0];
    	double centerLng = centerCoord[1];
    	
    	for(double[] pair : coordPair) {
    		double lat = pair[0];
    		double lng = pair[1];

//    		중심 좌표에 대한 각도 구하기
    		double angle = Math.atan2(lat-centerLat, lng-centerLng);
    		
//    		각도를 기준으로 정렬
    		
    	}
    	
    }
    
//  폴리곤의 시작과 끝을 맞춰서 반환하는 메소드
    public static List<double[]> settingStartandEnd(List<double[]> sortedList) {
    	
    	double[] firstCoord = sortedList.get(0);
		int size = sortedList.size();
		double[] lastCoord = sortedList.get(size-1);
		
    	if(!sortedList.isEmpty()&& 
    			(firstCoord[0] != lastCoord[0] // 시작점과 끝점이 안 맞을 경우 선이 꼬임!
    					|| firstCoord[1] != lastCoord[1])
    	   ) {
    		sortedList.add(firstCoord); // 다각형의 시작점과 끝점을 맞춤
    	}
    	return sortedList;
    } // settingStartandEnd
    
//   중심좌표 구하는 메소드
    public static double[] getCenterCoord(List<double[]> coordPair) {
//    	
    	int size = coordPair.size();
    	double[] centerCoord = null;
    	
    	double lat = 0;
    	double lng = 0;

//    	중심좌표 구하기
    	for(double[] pair : coordPair) {
    		lat += pair[0];
    		lng += pair[1];
    	}
    	
    	double centerLat = lat/size;
    	double centerLng = lng/size;
    	
    	centerCoord[0] = centerLat;
    	centerCoord[1] = centerLng;
    	
    	System.out.println(centerCoord.toString());
    	return centerCoord;
    	
    } // getCenterCoord
    
}
    

	

