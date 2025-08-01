package util.APIUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import com.google.gson.Gson;

import model.Data;
import model.Police;
import model.PoliceRaw;

public class PoliceAPI {

	List<Police> policeList = null;

	// 파출소 API 주소
	private static String PoliceAPI_URL = "https://api.odcloud.kr/api/15077036/v1/uddi:6b371c66-09a5-4efd-8445-bfd53672542e?page=1&perPage=2100&serviceKey=QvgnWQKUCrQ%2BqCLLRnIed%2BwfPlYFx7tS1JNhyY%2F9KwVyrLLMWGzLj565XUBiT6NYFl2kmqf6T%2B5BqoS6RVryLg%3D%3D";

	// 클라이언트 객체 생성
	private static HttpClient client = HttpClient.newHttpClient();

	public static void main(String[] args) throws IOException, InterruptedException {
		PoliceAPI policeAPI = new PoliceAPI();
		
		
		String str = String.valueOf(policeAPI.getPoliceAPI()).replaceAll("\"경찰서\"", "\"district\"");
		str = str.replaceAll("\"관서명\"", "\"village\"");
		str = str.replaceAll("\"구분\"", "\"role\"");
		str = str.replaceAll("\"시도청\"", "\"region\"");
		str = str.replaceAll("\"연번\"", "\"id\"");
		str = str.replaceAll("\"전화번호\"", "\"phone\"");
		str = str.replaceAll("\"주소\"", "\"address\"");
		
		Police police = new Police();
		List<Police> policeList = new ArrayList<Police>();
		
		Gson gson = new Gson();
		PoliceRaw station = new PoliceRaw();
		station = gson.fromJson(str, PoliceRaw.class);
		for (Data data : station.getData()) {
			police.setName(data.getVillage() + (data.getRole()));
			police.setLocation(data.getAddress());
			police.setRegionId();
			police.setLat(geoCoding(data.getAddress())[0]);
			police.setLog(geoCoding(data.getAddress())[1]);
		}
	}
	// API 가져오는 메서드
	public Object getPoliceAPI() throws IOException, InterruptedException {
		// 요청할 데이터 객체
		HttpRequest request = null;

		// 요청할 데이터
		// httpequest 반환
		request = HttpRequest.newBuilder().uri(URI.create(PoliceAPI_URL)).GET().build();

		HttpResponse response = client.send(request, BodyHandlers.ofString());
//		System.out.println(response.body());
		
//		System.out.println(str);
		
		
		
		

		return response.body();

	}

	public static Double[] geoCoding(String location) {
		if (location == null) {
			return null;
		};
		
		Geocoder geocoder = new Geocoder();
		// setAddress : 변환하려는 주소 (경기도 성남시 분당구 등)
		// setLanguate : 인코딩 설정
		
		GeocoderRequest geocoderRequest = 
				new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();	
		GeocodeResponse geocoderResponse;
		
		try {
			geocoderResponse = geocoder.geocode(geocoderRequest);
			
			if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {
				GeocoderResult geocoderResult = geocoderResponse.getResults().iterator().next();
				LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();
				
				Double[] coords = new Double[2];
				coords[0] = latitudeLongitude.getLat().doubleValue();
				coords[1] = latitudeLongitude.getLng().doubleValue();
				
				return coords;
			}
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
