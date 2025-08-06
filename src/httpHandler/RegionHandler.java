package httpHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        	int eIdx = query.indexOf("=");
        	String siName = null;
        	
        	List<Coord> coordList = null;
        	
        	if(query.contains("시")) {
            	int siIdx = query.indexOf("시");
            	guName = query.substring(siIdx+1).trim();
            	siName = query.substring(eIdx+1, siIdx).trim();
            	
//            	구 이름에 해당하는 모든 객체를 리스트에 담는다.
            	coordList = coordService.guCoordsList(guName);
            	System.out.println(coordList);
            	
//              좌표쌍들을 담는 리스트 
            	List<double[]> coordPairs = new ArrayList<double[]>();
            	
            	List<Coord> selectedCoordList = new ArrayList<Coord>();
            	
            	if(isDupGu(coordList)) {
            		String frontTwoId = filteringRegionIdForSiName(siName);
                	for(Coord coord : coordList) {
                		if(Integer.toString(coord.getRegionId())
                				.substring(0, 2).equals(frontTwoId)) {
                			selectedCoordList.add(coord);
                		}
                	}
//                	System.out.println(selectedCoordList);
                	
                	for(Coord coord : selectedCoordList) {
                		coordPairs.add(makeCoordPairArr(coord.getLat(), coord.getLog()));
                	}
//                	System.out.println(coordPairs);
                	
            		getSortedList(coordPairs); // 중심 좌표와의 각도에 대해 정렬
                	
            		String jsonResponse = gson.toJson(coordPairs);
                	HandlerUtil.sendResponse(exchange, jsonResponse);
                	
                	
            	} else {
            		for(Coord coord : coordList) {
            			coordPairs.add(makeCoordPairArr(coord.getLat(), coord.getLog()));
            		}
            		getSortedList(coordPairs);
            		
                	String jsonResponse = gson.toJson(coordPairs);
                	HandlerUtil.sendResponse(exchange, jsonResponse);
            	}
        	}
        	
    
    } catch(SQLException sqle) {
    	sqle.printStackTrace();
    }
} // handle
    
    public static double[] makeCoordPairArr(double lat, double log) {
    	double[] onePair = {lat, log};
    	return onePair;
    } // makeCoordPairArr
    
    public static boolean isDupGu(List<Coord> guList) {
    	
//    	사용자가 입력한 구의 regionId를 담은 Set을 만들었다.
    	Set<Integer> regionIdSet = new HashSet<Integer>();
    	for(Coord coord : guList) {
    		regionIdSet.add(coord.getRegionId());
    	}
    	if(regionIdSet.size()>1) { // regionId가 여러 종류일 경우
    		return true;
    	} else {
    		return false;
    	}
    	
    } // isDupGu
    
//  시 이름에 맞는 regionId 앞 두 자리를 반환하는 메소드
    public static String filteringRegionIdForSiName(String siName) {
    	switch (siName) {
    		case "서울": return Integer.toString(11);
    		case "인천": return Integer.toString(28);
    		case "울산": return Integer.toString(31);
    		case "대전": return Integer.toString(30);
    		case "광주": return Integer.toString(29);
    		case "대구": return Integer.toString(27);
    		case "부산": return Integer.toString(26);
    		default: return Integer.toString(0);
    	}

    } // filteringRegionIdForSiName

    
//  각도 기준 좌표 정렬 (이렇게 해야 선이 안 꼬임)
    public static List<double[]> getSortedList(List<double[]> coordPairs) {
    	
//    	중심 좌표 구하기
    	double[] centerCoord = getCenterCoord(coordPairs);
    	double centerLat = centerCoord[0];
    	double centerLng = centerCoord[1];
    	
//    	경도 기준 오름차순 정렬
    	Collections.sort(coordPairs, new Comparator<double[]>() {

			@Override
			public int compare(double[] o1, double[] o2) {
				return Double.compare(Math.atan2(o1[0]-centerLat, o1[1]-centerLng),
						Math.atan2(o2[0]-centerLat, o2[1]-centerLng));
			}
		});
    	
    	return coordPairs;
    	
    } // getSortedList
    
    
//  중심좌표 구하는 메소드
   public static double[] getCenterCoord(List<double[]> coordPairs) {
//   	
   	int size = coordPairs.size();
   	double[] centerCoord = new double[2];
   	
   	double latSum = 0;
   	double lngSum = 0;

//   	중심좌표 구하기
   	for(double[] pair : coordPairs) {
   		latSum += pair[0];
   		lngSum += pair[1];
   	}
   	
//  점들의 평균으로 중심점을 구한다.
   	double centerLat = latSum/size;
   	double centerLng = lngSum/size;
   	
   	centerCoord[0] = centerLat;
   	centerCoord[1] = centerLng;
   	
   	return centerCoord;
   	
   } // getCenterCoord
    
}
    
    

	

