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

		return jsonStr;
	}

	// JSON 데이터 파싱 후에 리스트에 넣는 메서드
	public List<Police> insertPoliceList(String jsonStr) {
		List<Police> policeList = new ArrayList<>();
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
		JsonArray jsonArray = jsonObject.getAsJsonArray("data");

		for (JsonElement elem : jsonArray) {
			JsonObject dataObject = elem.getAsJsonObject();

			String policeAddress = dataObject.get("관서명").getAsString() + " " + dataObject.get("구분").getAsString();

			String fullLocation = dataObject.get("주소").getAsString();

			int idx = fullLocation.indexOf("구");

			String sigu = (idx != -1) ? fullLocation.substring(0, idx + 1) : fullLocation;

			Police police = new Police();
			police.setPolice_address(policeAddress);
			police.setLocation(sigu);
			policeList.add(police);
		}

		return policeList;
	}

}
