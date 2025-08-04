package util.APIUtil;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.Police;

public class PoliceAPI {

	// 파출소 API 주소
	private static String PoliceAPI_URL = "https://api.odcloud.kr/api/15077036/v1/uddi:6b371c66-09a5-4efd-8445-bfd53672542e?page=1&perPage=2100&serviceKey=QvgnWQKUCrQ%2BqCLLRnIed%2BwfPlYFx7tS1JNhyY%2F9KwVyrLLMWGzLj565XUBiT6NYFl2kmqf6T%2B5BqoS6RVryLg%3D%3D";

	// 클라이언트 객체 생성
	private static HttpClient client = HttpClient.newHttpClient();

	// API에서 JSON 데이터 가져오는 메서드
	public String getPoliceAPI() throws IOException, InterruptedException {

		// 요청 데이터
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(PoliceAPI_URL)).GET().build();

		// 서버에 요청
		HttpResponse response = client.send(request, BodyHandlers.ofString());
		String jsonStr = (String) response.body();

		return jsonStr; // josn 문자열 반환
	}

	// json 문자열을 인자로 받아서 리스트에 넣는 메서드
	public List<Police> insertPoliceList(String jsonStr) {
		List<Police> policeList = new ArrayList<>();
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");

		for (JsonElement element : jsonArray) {
			JsonObject dataObject = element.getAsJsonObject();

			// 관서명 + 구분
			String policeAddress = dataObject.get("관서명").getAsString() + " " + dataObject.get("구분").getAsString();

			// 풀 주소
			String fullLocation = dataObject.get("주소").getAsString();

			// 서울시 강남구 인덱스 값 추출
			int siIdx = fullLocation.indexOf(" ");
			int guIdx = fullLocation.indexOf(" ", siIdx + 1);

			// 서울시 + 강남구 추출
			String si = fullLocation.substring(0, siIdx);
			String gu = fullLocation.substring(siIdx + 1, guIdx);
			String sigu = si + " " + gu;

			Police police = new Police();
			police.setPolice_address(policeAddress); // 관서 + 구분
			police.setLocation(sigu); // 시 + 구
			policeList.add(police);
		}

		return policeList;
	}

}
