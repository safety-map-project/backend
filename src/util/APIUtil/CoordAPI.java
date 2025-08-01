package util.APIUtil;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
			
			
			for(JsonElement ele : features) { 
				
				System.out.println(ele);
				
				String sig_kor_nm 
				= ele.getAsJsonObject()
				.get("properties")
				.getAsJsonObject()
				.get("SIG_KOR_NM")
				.getAsString();
				
				
				if(!sig_kor_nm.trim().contains("구")) {
					continue;
				}
				
//				지역코드
				String regionId 
				= ele.getAsJsonObject()
				.get("properties")
				.getAsJsonObject()
				.get("SIG_CD")
				.getAsString();
//				System.out.println("regionId 출력: " + regionId);
				
				
//				위도경도 쌍 배열을 요소로 가진 배열
				JsonArray coordinates 
					= ele.getAsJsonObject()
					.get("geometry")
					.getAsJsonObject()
					.get("coordinates")
					.getAsJsonArray().get(0)
					.getAsJsonArray();
				
				for(JsonElement coord : coordinates) {
					double lat = coord.getAsJsonArray().get(1).getAsDouble(); // 위도
					double log = coord.getAsJsonArray().get(0).getAsDouble(); // 경도
					coordList.add(new Coord(0, lat, log, regionId));
				}
			
				return coordList;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	} // makeCoordList
	
//	public static void main(String[] args) {
//		System.out.println(makeCoordList());
//	}
	
}
