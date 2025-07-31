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
				
				String gu = sig_kor_nm;
				if(gu.contains("시")) {
					int idx = sig_kor_nm.indexOf("시");
					gu = sig_kor_nm.substring(0, idx+1).trim(); // 시까지 자름
				} else {
					gu = sig_kor_nm.trim();
				}
				
//				properties 객체에서 지역아이디랑 구만 꺼내서 생성자에 넣는다.
				if(gu.endsWith("구")) {
					Region region = new Region(regionId, gu);
					regionList.add(region);
				} 
			}
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return regionList;
			
			
			
	}
	
} // class

