package codeTest;

import java.io.IOException;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

public class Test1 {

	public static void main(String[] args) {
		String location = "대전광역시 유성구 대학로 99"; // 실제 도로명 주소
		Float[] coords = findGeoPoint(location); // CommonUtil 제거

		if (coords != null) {
			System.out.println(location + ": " + coords[0] + ", " + coords[1]);
		} else {
			System.out.println("좌표 변환 실패: " + location);
		}
	}

	public static Float[] findGeoPoint(String location) {

		if (location == null)
			return null;

		// setAddress : 변환하려는 주소 (경기도 성남시 분당구 등)
		// setLanguate : 인코딩 설정
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko")
				.getGeocoderRequest();

		try {
			Geocoder geocoder = new Geocoder();
			GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

			if (geocoderResponse.getStatus() == GeocoderStatus.OK && !geocoderResponse.getResults().isEmpty()) {
				GeocoderResult geocoderResult = geocoderResponse.getResults().iterator().next();
				LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();

				Float[] coords = new Float[2];
				coords[0] = latitudeLongitude.getLat().floatValue(); // 위도 
				coords[1] = latitudeLongitude.getLng().floatValue(); // 경도 
				return coords;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
