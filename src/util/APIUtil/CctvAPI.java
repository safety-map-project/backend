package util.APIUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Cctv;

public class CctvAPI {
	Gson gson = new Gson();
	
	public static List<Cctv> save() {
		List<Cctv> cctvList = new ArrayList<Cctv>();	
		JsonArray cctvs = getCctvArray();
		
		for (JsonElement cctv : cctvs) {
			Cctv cctvObj = new Cctv();
			JsonObject obj = cctv.getAsJsonObject();
			JsonElement location = obj.get("LOCATION");
			cctvObj.setLocation(!location.isJsonNull() ? location.getAsString() : "");
			cctvObj.setRegionId(obj.get("REGIONID").getAsInt());
			cctvObj.setLat(obj.get("LAT").getAsDouble());
			cctvObj.setLog(obj.get("LOG").getAsDouble());
			cctvList.add(cctvObj);
		}
		
		return cctvList;
	};
	
	
	public static JsonArray getCctvArray() {
		
		JsonArray cctvs = null;
		FileReader reader = null;
//		String[] files = {
//				"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\seoul.json",
//				"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\busan.json",
//				"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\daegu.json",
//				"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\incheon.json",
//				"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\gwangju.json",
//				"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\daejeon.json",
//				"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\ulsan.json"
//		};
		
		try {
			File file = new File("C:\\Users\\Administrator\\Documents\\middle\\CCTV\\seoul.json");
			
			reader = new FileReader(file);
			
			cctvs = JsonParser.parseReader(reader).getAsJsonArray();
		}catch (IOException ioe){
			ioe.printStackTrace();
		}finally {
			try {
				reader.close();
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}
		
		return cctvs;
	}
}
