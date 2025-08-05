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

	private static final String filePath = "C:\\pub2504\\eclipse_workspace\\project\\result.json";

	public List<Police> insertPoliceList() throws IOException {
		List<Police> policeList = new ArrayList<>();
		Gson gson = new Gson();

		// JSON 파일 읽기
		FileReader reader = new FileReader(filePath);
		JsonArray locationArray = gson.fromJson(reader, JsonArray.class);

		for (int i = 0; i < locationArray.size(); i++) {
			JsonObject locObj = locationArray.get(i).getAsJsonObject();

			String policeName = locObj.get("관서명").getAsString();
			String category = locObj.get("구분").getAsString();
			String policeAddress = policeName + " " + category;

			Police police = new Police();
			police.setName(policeAddress); // 관서명 + 구분 >> name
			police.setLocation(locObj.get("주소").getAsString()); // 주소 >> location
			police.setLat(locObj.get("Latitude").getAsDouble()); // 위도
			police.setLog(locObj.get("Longitude").getAsDouble()); // 경도

			policeList.add(police);
		}

		return policeList;
	}
}