package util.APIUtil;

import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SiAPI {

	private static final String SI_API_URL = "http://apis.data.go.kr/1741000/StanReginCd/getStanReginCdList?ServiceKey=bafEXsp3A%2FiNM6SrdTReCSgGSp3PZcxqoiD08onBtPTnKgLGfaCfkXnJa15dbR8zVjWpmN99qG5QMrpT%2FjWbuQ%3D%3D&type=json&pageNo=1&numOfRows=3&flag=Y&locatadd_nm=";
	
	public static String getSiCode(String si) {
		
		Request request = new Request.Builder().url(SI_API_URL + si).build();
		Response response = null;
		String json = null;
		
		try {
			
			OkHttpClient client = new OkHttpClient();
			response = client.newCall(request).execute();
			
			json = Objects.requireNonNull(response.body().string());
//			System.out.println(json);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
			JsonArray jsonArr = jsonObj.getAsJsonArray("StanReginCd").get(1).getAsJsonObject().getAsJsonArray("row");
			
			for(JsonElement ele : jsonArr) {
				JsonObject obj = ele.getAsJsonObject();
				System.out.println(obj.get("sido_cd").getAsString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return json;
		
	} // getSiCode()
	
}
