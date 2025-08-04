package util.APIUtil;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Coord;

public class CoordAPI {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static List<Coord> makeCoordList() {
		
		List<Coord> coordList = new ArrayList<Coord>();
		
		try(Reader reader = new FileReader("C:\\Users\\Administrator\\git\\backend\\src\\util\\APIUtil\\sigu.json")) {
			
			JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
//			System.out.println(obj);
			JsonArray features = obj.get("features").getAsJsonArray();
			
			for(JsonElement feature : features) { 
//				System.out.println(ele);
				JsonObject featureObj = feature.getAsJsonObject();
				JsonObject properties = featureObj.getAsJsonObject("properties");
				
				String gu_name 
				= properties
				.get("SIG_KOR_NM")
				.getAsString()
				.trim();
				
				if(gu_name.endsWith("시")) {
					continue;
				} else if(gu_name.endsWith("구")&&gu_name.contains("시")) {
					continue;
				} else if(!gu_name.endsWith("구")) {
					continue;
				}
				
//				지역코드
				int regionId 
				= properties
				.get("SIG_CD")
				.getAsInt();
		
				
//				위도경도 쌍 배열을 요소로 가진 배열
				JsonArray coordinates 
					= featureObj
					.getAsJsonObject("geometry")
					.getAsJsonArray("coordinates");
//				System.out.println(coordinates.size());
				
				JsonArray outerRing = coordinates.get(0).getAsJsonArray();
//				System.out.println(outerRing.size());
				
				for(JsonElement points : outerRing) {
					JsonArray coord = points.getAsJsonArray();
					double lat = coord.get(1).getAsDouble();
					double log = coord.get(0).getAsDouble();
					coordList.add(new Coord(0, lat, log, regionId, gu_name));
				}
//				System.out.println("coordList size : "+coordList.size());
//				coordList.stream().forEach(System.out::println);
			
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return coordList;

		
	} // makeCoordList
	
	public static void main(String[] args) {
//		System.out.println(makeCoordList());
		
		
		List<Coord> cList = makeCoordList();
//		System.out.println("총 Coord 개수: " + cList.size());
//		System.out.println(cList);

		cList.stream().forEach(System.out::println);
//
	}
	
}
