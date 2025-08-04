package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.Crime;
import model.Region;
import service.CrimeService;
import service.RegionService;
import service.Impl.CrimeServiceImpl;
import service.Impl.RegionServiceImpl;
import util.APIUtil.CrimeAPI;

public class CrimeServiceTest {

	public static void main(String[] args) {
	
		CrimeService crimeService = new CrimeServiceImpl();
		RegionService regionService = new RegionServiceImpl();
		
		try {

			// Crime 객체를 crimeList에 담음
			List<Crime> crimeList = CrimeAPI.getCrimeList(CrimeAPI.getCrimeAPI());
			// map String, String hashMap 객체 생성
			Map<String, String> regionMap = new HashMap();
			// regionService listRegion() 호출해 region 객체를 담는 list에 담음
			List<Region> regionList = regionService.listRegion();
			
			// for문을 돌려서 region 객체 하나씩 꺼냄
			for(Region region : regionList) {
				// regionMap에 키,값으로 regionId, gu, si를 put함
				regionMap.put(region.getRegionId(), region.getSi() + " " + region.getGu());
			}

			Set<Map.Entry<String, String>> entrySet = regionMap.entrySet();
			
			Iterator<Map.Entry<String, String>> it = entrySet.iterator();
			while(it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				addRegionIdToCrime(crimeService, entry);
			}
				
			// db crime 테이블에 crime데이터 넣음
			// regionId는 임시로 넣음
//			for(Crime crime : crimeList) {
//				crimeService.insertCrime(
//					new Crime(
//							0, crime.getCrimeYear(), crime.getCrimeType(), crime.getCrimeCount(), 
//							29155, crime.getRegion())
//				);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	
	}
	
	private static void addRegionIdToCrime(CrimeService crimeService, Map.Entry<String, String> entry) throws Exception {
		
		Map<String, String> regionMap = Map.ofEntries(
			Map.entry("서울특별시 종로구", "서울 종로구"),
			Map.entry("서울특별시 중구", "서울 중구"),
			Map.entry("서울특별시 용산구", "서울 용산구"),
			Map.entry("서울특별시 성동구", "서울 성동구"),
			Map.entry("서울특별시 광진구", "서울 광진구"),
			Map.entry("서울특별시 동대문구", "서울 동대문구"),
			Map.entry("서울특별시 중랑구", "서울 중랑구"),
			Map.entry("서울특별시 성북구", "서울 성북구"),
			Map.entry("서울특별시 강북구", "서울 강북구"),
			Map.entry("서울특별시 도봉구", "서울 도봉구"),
			Map.entry("서울특별시 노원구", "서울 노원구"),
			Map.entry("서울특별시 은평구", "서울 은평구"),
			Map.entry("서울특별시 영등포구", "서울 영등포구"),
			Map.entry("서울특별시 동작구", "서울 동작구"),
			Map.entry("서울특별시 관악구", "서울 관악구"),
			Map.entry("서울특별시 서초구", "서울 서초구"),
			Map.entry("서울특별시 강남구", "서울 강남구"),
			Map.entry("서울특별시 송파구", "서울 송파구"),
			Map.entry("서울특별시 강동구", "서울 강동구"),
			Map.entry("부산광역시 중구", "부산 중구"),
			Map.entry("부산광역시 서구", "부산 서구"),
			Map.entry("부산광역시 동구", "부산 동구"),
			Map.entry("부산광역시 영도구", "부산 영도구"),
			Map.entry("부산광역시 부산진구", "부산 부산진구"),
			Map.entry("부산광역시 동래구", "부산 동래구"),
			Map.entry("부산광역시 남구", "부산 남구"),
			Map.entry("부산광역시 북구", "부산 북구"),
			Map.entry("부산광역시 해운대구", "부산 해운대구"),
			Map.entry("부산광역시 사하구", "부산 사하구"),
			Map.entry("부산광역시 금정구", "부산 금정구"),
			Map.entry("부산광역시 강서구", "부산 강서구"),
			Map.entry("부산광역시 연제구", "부산 연제구"),
			Map.entry("부산광역시 수영구", "부산 수영구"),
			Map.entry("부산광역시 사상구", "부산 사상구"),
			Map.entry("대구광역시 중구", "대구 중구"),
			Map.entry("대구광역시 동구", "대구 동구"),
			Map.entry("대구광역시 서구", "대구 서구"),
			Map.entry("대구광역시 남구", "대구 남구"),
			Map.entry("대구광역시 북구", "대구 북구"),
			Map.entry("대구광역시 수성구", "대구 수성구"),
			Map.entry("대구광역시 달서구", "대구 달서구"),
			Map.entry("인천광역시 중구", "인천 중구"),
			Map.entry("인천광역시 동구", "인천 동구"),
			Map.entry("인천광역시 서구", "인천 서구"),
			Map.entry("인천광역시 미추홀구", "인천 미추홀구"),
			Map.entry("인천광역시 연수구", "인천 연수구"),
			Map.entry("인천광역시 남동구", "인천 남동구"),
			Map.entry("인천광역시 부평구", "인천 부평구"),
			Map.entry("인천광역시 계양구", "인천 계양구"),
			Map.entry("광주광역시 동구", "광주 동구"),
			Map.entry("광주광역시 서구", "광주 서구"),
			Map.entry("광주광역시 남구", "광주 남구"),
			Map.entry("광주광역시 북구", "광주 북구"),
			Map.entry("광주광역시 광산구", "광주 광산구"),
			Map.entry("대전광역시 중구", "대전 중구"),
			Map.entry("대전광역시 동구", "대전 동구"),
			Map.entry("대전광역시 서구", "대전 서구"),
			Map.entry("대전광역시 유성구", "대전 유성구"),
			Map.entry("대전광역시 대덕구", "대전 대덕구"),
			Map.entry("울산광역시 중구", "울산 중구"),
			Map.entry("울산광역시 동구", "울산 동구"),
			Map.entry("울산광역시 남구", "울산 남구"),
			Map.entry("울산광역시 북구", "울산 북구")
		);
		
		String regionVal = entry.getValue();
		String dbRegion = regionMap.get(regionVal);
		
		if(dbRegion == null) return;
		
		for(Crime crime : crimeService.listCrime()) {
			
			if(entry.getValue().equals(regionVal) && crime.getRegion().equals(dbRegion)) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			}
			
		}
		
	} // addRegionIdToCrime
	
}
