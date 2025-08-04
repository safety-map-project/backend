package util.APIUtil;

import java.io.FileReader;
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

	private static final String PoliceAPI_URL = "https://api.odcloud.kr/api/15077036/v1/uddi:6b371c66-09a5-4efd-8445-bfd53672542e?page=1&perPage=2100&serviceKey=QvgnWQKUCrQ%2BqCLLRnIed%2BwfPlYFx7tS1JNhyY%2F9KwVyrLLMWGzLj565XUBiT6NYFl2kmqf6T%2B5BqoS6RVryLg%3D%3D";
	private static final String locationFilePath = "C:\\pub2504\\eclipse_workspace\\project\\location.json";

	private static final HttpClient client = HttpClient.newHttpClient();

	public String getPoliceAPI() throws IOException, InterruptedException {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(PoliceAPI_URL)).GET().build();
		HttpResponse<?> response = client.send(request, BodyHandlers.ofString());
		return (String) response.body();
	}

	public List<Police> insertPoliceList(String jsonStr) throws IOException {
		List<Police> policeList = new ArrayList<>();
		Gson gson = new Gson();

		// json 데이터 파싱 
		JsonObject jsonObject = gson.fromJson(jsonStr, JsonObject.class);
		JsonArray apiArray = jsonObject.getAsJsonArray("data");

		// 엑셀 데이터 파싱 
		FileReader reader = new FileReader(locationFilePath); // 2045개
		JsonArray locationArray = gson.fromJson(reader, JsonArray.class); // 2045개

		int count = Math.min(apiArray.size(), locationArray.size());

		for (int i = 0; i < count; i++) {
			JsonObject apiObj = apiArray.get(i).getAsJsonObject();
			JsonObject locObj = locationArray.get(i).getAsJsonObject();

			JsonElement regionElement = locObj.get("RegionId");
			if (regionElement == null || regionElement.isJsonNull()) {
				continue;
			}

			String policeName = apiObj.get("관서명").getAsString();
			String policeType = apiObj.get("구분").getAsString();
			String policeAddress = policeName + " " + policeType; 

			Police police = new Police();
			police.setPolice_address(policeAddress); // 관서명+구분> Name
			police.setLocation(locObj.get("주소").getAsString()); // > Location
			police.setLat(locObj.get("Lat").getAsDouble()); // lat
			police.setLog(locObj.get("Log").getAsDouble()); // log
			police.setRegionId(regionElement.getAsInt()); //regionid

			policeList.add(police);
		}

		return policeList;
	}
}