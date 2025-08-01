package util.APIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.Crime;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CrimeAPI {
	
	// crime api url
	private static final String CRIME_API_URL = "http://apis.data.go.kr/B554626/CrimeAnalysis/getCrimeAnalysis?serviceKey=bafEXsp3A%2FiNM6SrdTReCSgGSp3PZcxqoiD08onBtPTnKgLGfaCfkXnJa15dbR8zVjWpmN99qG5QMrpT%2FjWbuQ%3D%3D&type=JSON&sht=T188653019959688&statsYr=2023&artcl=B001,B002,B003,B004,B005,B006,B007,B008,B009,B010,B011,B012,B013,B014,B015,B016,B017,B018,B019,B020,B021,B022,B023,B024,B025,B026,B027,B028,B029,B030,B031,B032,B033,B034,B035,B036,B037,B038,B039,B040,B041,B042,B043,B044,B045,B046,B047,B048,B049,B050,B051,B052,B053,B054,B055,B056,B057,B058,B059,B060,B061,B062,B063,B064,B065,B066,B067,B068,B069,B070,B071,B072,B073,B074,B075,B076,B077,B078,B079,B080,B081,B082&clsf=B006,B007";
	
	public static String getCrimeAPI() {

		// 요청 객체 생성
		Request request = new Request.Builder().url(CRIME_API_URL).build();
		Response response = null;
		String json = null;

		try {
			// http 응답을 받는 클라이언트 객체 생성
			OkHttpClient client = new OkHttpClient();
			// 클라이언트 응답
			response = client.newCall(request).execute();

			// 응답 바디 String에 넣음
			json = Objects.requireNonNull(response.body()).string();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;

	} // getCrimeAPI

	public static List<Crime> getCrimeList(String json) {

		// 강력범죄(흉악) crime 객체를 담는 list
		List<Crime> crimeHeinousList = new ArrayList();
		// 강력범죄(폭행) crime 객체를 담는 list
		List<Crime> crimeAttackList = new ArrayList();
		
		// gson 객체 생성
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// String을 json으로 변환 후 jsonObject에 넣음
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		// json 파싱
		JsonObject crimeObj = jsonObj.getAsJsonObject("response").getAsJsonObject("body").getAsJsonObject("items")
				.getAsJsonObject("item");

		// 범죄발생지역 배열
		JsonArray cityNameArray = crimeObj.getAsJsonArray("artcl");
		// 범죄발생지의 범죄 건수
		JsonArray crimeCountArray = crimeObj.getAsJsonArray("statsVl");
		JsonArray crimeType = crimeObj.getAsJsonArray("clsf");
		
		// 지역별 강력범죄(흉악) 건수
		JsonElement crimeHeinousCount = crimeCountArray.get(0);
		// 지역별 강력범죄(폭행) 건수
		JsonElement crimeAttackCount = crimeCountArray.get(5); 
		
		int crimeHeinousCountArrsize = crimeHeinousCount.getAsJsonArray().size();
		for(int i=0; i<crimeHeinousCountArrsize; i++) {
			
			// 강력범죄(흉악) 소계
			int sumCrimeHeinousCount = crimeHeinousCount.getAsJsonArray().get(i).getAsInt();
			// 강력범죄(폭행) 소계
			int sumCrimeAttackCount = crimeAttackCount.getAsJsonArray().get(i).getAsInt();
			// 범죄발생년도
			int statsYr = crimeObj.get("statsYr").getAsInt();
			
			// 강력범죄(흉악) crime 객체 생성
			Crime heinousCrime = new Crime(
				0,
				statsYr,
				0,
				"강력범죄(흉악)",
				sumCrimeHeinousCount
			);
				
			// 강력범죄(폭행) crime 객체 생성
			Crime attackCrime = new Crime(
				0,
				statsYr,
				0,
				"강력범죄(폭행)",
				sumCrimeAttackCount
			);
				
			// crime 객체 각 리스트에 넣음
			crimeHeinousList.add(heinousCrime);
			crimeAttackList.add(attackCrime);
			
		}
		
		return setCrimeRegionName(cityNameArray, crimeHeinousList, crimeAttackList);

	} // getCrimeList
	
	// crime 객체에 각 region을 넣는 메서드
	private static List<Crime> setCrimeRegionName(JsonArray cityNameArray, List<Crime> crimeHeinousList, List<Crime> crimeAttackList){
	
		// 강력범죄(흉악, 폭행) crime 객체를 담는 list
		List<Crime> allCrimeList = new ArrayList();
		
		int retionNameSize = cityNameArray.size();
		for(int i=0; i<retionNameSize; i++) {
			crimeHeinousList.get(i).setRegion(
					cityNameArray.getAsJsonArray().get(i).getAsJsonArray().get(0).getAsString() +
					" " + cityNameArray.getAsJsonArray().get(i).getAsJsonArray().get(1).getAsString()
			);
			crimeAttackList.get(i).setRegion(
					cityNameArray.getAsJsonArray().get(i).getAsJsonArray().get(0).getAsString() +
					" " + cityNameArray.getAsJsonArray().get(i).getAsJsonArray().get(1).getAsString()
			);
		}
		
		allCrimeList.addAll(crimeHeinousList);
		allCrimeList.addAll(crimeAttackList);
		
		return allCrimeList;
	}
	
	public static void main(String[] args) {
//		getCrimeList(getCrimeAPI());
		getCrimeList(getCrimeAPI()).stream().forEach(System.out::println);
	}

}
