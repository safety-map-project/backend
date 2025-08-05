package util.APIUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Cctv;
import util.ConnectionUtil;

public class CctvAPI {
	Gson gson = new Gson();
	
	public static List<Cctv> save() {
		List<Cctv> cctvList = new ArrayList<Cctv>();	
		JsonArray cctvs = getCctvArray();
		
		String sql = " insert into MAP.CCTV " +
					" values(MAP.SEQ_CCTV, ?, ?, ?, ?)";
		
		for (JsonElement cctv : cctvs) {
			Cctv cctvObj = new Cctv();
			JsonObject obj = cctv.getAsJsonObject();
			JsonElement id = obj.get("CCTVID");
			if (id.getAsInt() == 882) {
				JsonElement location = obj.get("LOCATION");
				cctvObj.setLocation(!location.isJsonNull() ? location.getAsString() : "");
				cctvObj.setRegionId(obj.get("REGIONID").getAsInt());
				cctvObj.setLat(obj.get("LAT").getAsDouble());
				cctvObj.setLog(obj.get("LOG").getAsDouble());
				cctvList.add(cctvObj);
			}else {
				continue;
			}
		}
		
		return cctvList;
	}
	
	public static JsonArray getCctvArray() {
		
		Reader reader = null;
		JsonArray cctvs = new JsonArray();
		
		try {
			String[] files = {
					"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\daegu.json",
					"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\busan.json",
					"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\daegu.json",
					"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\incheon.json",
					"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\gwangju.json",
					"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\daejeon.json",
					"C:\\Users\\Administrator\\Documents\\middle\\CCTV\\ulsan.json"
					};
			
			for (String filepath : files) {
				File file = new File(filepath);
			
				reader = new FileReader(file);
				
				cctvs.add(JsonParser.parseReader(reader).getAsJsonArray());				
			};
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
