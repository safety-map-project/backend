package util.APIUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Region;

public class RegionAPI {
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static List<Region> makeRegionList() {
		
		List<Region> regionList = new ArrayList<Region>();
		
		try(Reader reader = new FileReader("C:\\Users\\Administrator\\git\\backend\\src\\util\\APIUtil\\sigu.json")) {

//			읽어온 json 파일을 파싱해서 JsonObject로
			JsonObject objEle = JsonParser.parseReader(reader).getAsJsonObject();
			
//			object 안에 있는 features 배열을 가져옴
			JsonArray features = objEle.get("features").getAsJsonArray();
			
			for(JsonElement ele : features) {
				
//				features 배열 안의 요소가 객체이기 때문에 여기서 Object로 받아준다.
				JsonObject obj = ele.getAsJsonObject();
				
//				그 객체 안에 properties라는 객체가 있는데
//				properties 객체는 지역아이디랑 시/군/구를 가지고 있다.
//				그래서 객체로 접근하는 것이다.
				JsonObject properties = obj.get("properties").getAsJsonObject();
				
				int regionId = properties.get("SIG_CD").getAsInt();
				String sig_kor_nm = properties.get("SIG_KOR_NM").getAsString();
				
				String gu = sig_kor_nm.trim();
				
				if(gu.endsWith("시")) { 
					continue;
//					
				} else if(gu.contains("시") && gu.endsWith("구")) { // ex.수원시 장안구
					continue;
					
				} else if(gu.contains("기장군") || gu.contains("달성군") ||
						gu.contains("군위군")||gu.contains("강화군")||gu.contains("옹진군")||gu.contains("울주군")) {
					
						regionList.add(new Region(regionId, gu, verifyRegionId(regionId)));
					
				} else { // ex. OO구
					regionList.add(new Region(regionId, gu, verifyRegionId(regionId)));
				}
					
					
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return regionList;
			
	} // makeRegionList
	
	public static String verifyRegionId(int regionId) {
		switch(Integer.toString(regionId).substring(0, 2)) {
			case "11" : return "서울특별시";
			case "28" : return "인천광역시";
			case "31" : return "울산광역시";
			case "30" : return "대전광역시";
			case "29" : return "광주광역시";
			case "27" : return "대구광역시";
			case "26" : return "부산광역시";
			default: return "광역시 아님!";
			
		}
	}
	
	
	public static void main(String[] args) {
//		System.out.println(makeRegionList());
	}
	
} // class

