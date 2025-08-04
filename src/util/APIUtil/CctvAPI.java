package util.APIUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CctvAPI {
	JsonArray cctvs;
	
	public JsonArray cctvArray() {
		File file = null;
		Reader reader = null;
		
		
		try {
			file = new File("../file/CCTV.json");
			reader = new FileReader(file);
			
			Gson gson = new Gson();
			JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
			JsonArray cctvs = obj.getAsJsonArray();
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
