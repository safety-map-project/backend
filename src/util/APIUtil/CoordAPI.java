package util.APIUtil;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
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
		
		try(Reader reader = new FileReader("C:\\Users\\Administrator\\git\\backend\\src\\util\\APIUtil\\sigu.json")) {
			
			JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
			JsonArray features = obj.get("features").getAsJsonArray();
			
			List<Coord> coordList = new ArrayList<Coord>();
			for(JsonElement ele : features) { 
				
//				SIG_CD 
				String regionId 
				= ele.getAsJsonObject()
				.get("properties")
				.getAsJsonObject()
				.get("SIG_CD")
				.getAsString();
//				System.out.println(regionId);
				
//				coordinates(polygon 좌표) 배열
				JsonArray coordinates 
					= ele.getAsJsonObject()
					.get("geometry")
					.getAsJsonObject()
					.get("coordinates")
					.getAsJsonArray().get(0)
					.getAsJsonArray();
				
				int size = coordinates.size();
				for(int i=0; i<size; i++) {
					double lat 
					= coordinates.get(i)
					.getAsJsonArray()
					.get(1).getAsDouble();
					
					double log
					= coordinates.get(i)
					.getAsJsonArray()
					.get(0).getAsDouble();
					
					coordList.add(new Coord(0, lat, log, regionId));
				}
				
			} 
			
			return coordList;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	} // makeCoordList
	
//	public static void main(String[] args) {
//		System.out.println(makeCoordList());
//	}
	
}
