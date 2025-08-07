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

import org.openqa.selenium.bidi.network.ResponseData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Coord;
import model.Crime;
import model.CrimeAvg;
import service.CoordService;
import service.CrimeService;
import service.Impl.CoordServiceImpl;
import service.Impl.CrimeServiceImpl;
import util.HandlerUtil;

public class RegionHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
    	
    	if (exchange.getRequestMethod().equals("GET") ) {
    	
	    	//HandlerUtil.optionsEquals(exchange);
	    	
	    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        CoordService coordService = new CoordServiceImpl();
	        
	        try {
	        	
	        	String query = exchange.getRequestURI().getQuery();
	        	System.out.println(query);
	        	String guName = null;
	        	int eIdx = query.indexOf("=");
	        	String siName = null;
	        	
	        	if(query.contains("시")) {
	            	int siIdx = query.indexOf("시");
	            	guName = query.substring(siIdx+1).trim();
	            	siName = query.substring(eIdx+1, siIdx).trim();
	            	
	//            	구 이름에 해당하는 모든 객체를 리스트에 담는다.
	            	List<Coord> coordList = coordService.guCoordsList(guName);
	            	
	//              최종적으로 프론트에 전달할 좌표쌍들을 담는 리스트 
	            	List<double[]> coordPairs = new ArrayList<double[]>();
//	            	
//	            	사용자가 선택한 시에 존재하는 구의 coord 객체만 골라 담을 리스트.
	            	List<Coord> selectedCoordList = new ArrayList<Coord>();
	            	
	            	if(isDupGu(coordList)) { // 중복 구일 경우(중구, 동구, 서구...등등)
	            		
	            		
//	            		시를 구분하기 위해 지역코드 앞 두자리를 구한다.
	            		String frontTwoId = filteringRegionIdForSiName(siName);
	                	for(Coord coord : coordList) {
	                		if(Integer.toString(coord.getRegionId())
	                				.substring(0, 2).equals(frontTwoId)) {
	                			selectedCoordList.add(coord);
	                		}
	                	}
	                	
	                	for(Coord coord : selectedCoordList) {
	                		coordPairs.add(makeCoordPairArr(coord.getLat(), coord.getLog()));
	                	}
	                	
	            		getSortedList(coordPairs); // 중심 좌표와의 각도에 대해 정렬
	                	
	            		Map<String, Object> responseData = new HashMap<String, Object>();
	            		
	            		responseData.put("centerCoords", getCenterCoord(coordPairs));
	            		responseData.put("coords", coordPairs);
	            		responseData.put("zone", separationZone(selectedCoordList));
	            		responseData.put("existRegionTF", thisGuExistInSi(siName, guName, selectedCoordList));
	            		
	                	String jsonResponse = gson.toJson(responseData);
	                	HandlerUtil.sendResponse(exchange, jsonResponse);
	                	
	                	
	            	} else { // 유일한 구일 경우
	            		
//	            		coordList = coordService.guCoordsList(guName);
	            		for(Coord coord : coordList) {
	            			coordPairs.add(makeCoordPairArr(coord.getLat(), coord.getLog()));
	            		}
	            		getSortedList(coordPairs);
	            		
	            		Map<String, Object> responseData = new HashMap<String, Object>();
	            		
	            		responseData.put("centerCoords", getCenterCoord(coordPairs));
	            		responseData.put("coords", coordPairs);
	            		responseData.put("zone", separationZone(coordList));
	            		responseData.put("existRegionTF", thisGuExistInSi(siName, guName, coordList));
	            		
	                	String jsonResponse = gson.toJson(responseData);
	                	HandlerUtil.sendResponse(exchange, jsonResponse);
	            	}
	        	}
	        	
	    
	    } catch(SQLException sqle) {
	    	sqle.printStackTrace();
	    }
	        
    } // if
    	
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
    
//    사용자가 입력한 시/구의 구가 시에 존재하는 구인지 판단하는 메소드
    public static boolean thisGuExistInSi(String siName, String guName, List<Coord> cList){
    	
//    	사용자가 입력한 구에 대한 coord 객체 리스트
    	List<Coord> coordList = cList;
    	
//		사용자가 입력한 구의 지역코드 앞 두 자리
		String userRid = filteringRegionIdForSiName(siName);
		
    	for(Coord coord : coordList) {
    		
//    		cList에서 가져온 regionId
    		String rid = Integer.toString(coord.getRegionId()).substring(0, 2);
    	
    		// 사용자가 입력한 시의 regionId 앞 두 글자와 
//    		사용자가 입력한 구가 존재하는 시들의 regionId 앞 두글자를 비교해서
//    		모든 객체에서 찾아본 후 있으면 return true, 없으면 false를 반환
//    		=> 프론트에 전달(이 값이 false로 보내지면 프론트에서 폴리곤을 띄우지 않는다.)
    		if(rid.equals(userRid)) return true;
    	} 
		return false;
    } // thisGuExistInSi
    
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
    
//    범죄 평균 내서 안전구역/위험구역 구분
    public static String separationZone(List<Coord> coordList) {
    	CrimeService crimeService = new CrimeServiceImpl();
    	try {
//    		모든 crime 객체를 가져온다
			List<Crime> allCrimeList = crimeService.listCrime();
			
//			coordList에서 아무 객체나 가져온 후 regionId를 뽑는다.
//			애초에 인자로 받는 coordList는 전부 같은 지역이기 때문에 아무거나 가져와도 상관 없다.
			int regionId = coordList.get(3).getRegionId(); 
			
//			crimeService의 getCrimeCount 메소드를 이용하여 
//			사용자가 입력한 지역의 범죄 종류 별 범죄 건수를 가져온다. 
			List<Integer> crimeCounts = crimeService.getCrimeCount(regionId);
			int regionCrimeCount = 0; // 범죄 건수 초기화
			
//			모든 범죄 건수를 더한다.
			for(int count : crimeCounts) {
				regionCrimeCount += count;
			}
			
//			시 별 범죄 평균
			CrimeAvg avg = crimeService.calculateCrimeAverage(allCrimeList);
			switch(Integer.toString(regionId).substring(0, 2)) {
				case "11": return regionCrimeCount > avg.getSeoulCrimeAvg() ? "danger" : "safe";
				case "28": return regionCrimeCount > avg.getIncheonCrimeAvg() ? "danger" : "safe";
				case "31": return regionCrimeCount > avg.getUlsanCrimeAvg() ? "danger" : "safe";
				case "30": return regionCrimeCount > avg.getDaejeonCrimeAvg() ? "danger" : "safe";
				case "29": return regionCrimeCount > avg.getGwangjuCrimeAvg() ? "danger" : "safe";
				case "27": return regionCrimeCount > avg.getDaeguCrimeAvg() ? "danger" : "safe";
				case "26": return regionCrimeCount > avg.getBusanCrimeAvg() ? "danger" : "safe";
				default: return "광역시가 아님";
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return "에러 발생";
    	
    }

    
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
    
    
//  모든 좌표들의 평균을 구해서 중심 좌표를 구하는 메소드
   public static double[] getCenterCoord(List<double[]> coordPairs) {
//   	
   	int size = coordPairs.size();
   	double[] centerCoord = new double[2];
   	
   	double latSum = 0; // 위도의 합
   	double lngSum = 0; // 경도의 합

//   	중심좌표 구하기
   	for(double[] pair : coordPairs) {
   		latSum += pair[0]; // 위도
   		lngSum += pair[1]; // 경도
   	}
   	
//  점들의 평균으로 중심점을 구한다.
   	double centerLat = latSum/size;
   	double centerLng = lngSum/size;
   	
   	centerCoord[0] = centerLat;
   	centerCoord[1] = centerLng;
   	
   	return centerCoord;
   	
   } // getCenterCoord
    
}
    
    

	

