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
import util.APIUtil.SiAPI;

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
		
		for(Crime crime : crimeService.listCrime()) {
			
			if(entry.getValue().equals("서울특별시 용산구") && crime.getRegion().equals("서울 용산구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 광진구") && crime.getRegion().equals("서울 광진구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 동대문구") && crime.getRegion().equals("서울 동대문구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 중랑구") && crime.getRegion().equals("서울 중랑구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 성북구") && crime.getRegion().equals("서울 성북구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 강북구") && crime.getRegion().equals("서울 강북구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 도봉구") && crime.getRegion().equals("서울 도봉구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 성동구") && crime.getRegion().equals("서울 성동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 노원구") && crime.getRegion().equals("서울 노원구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 은평구") && crime.getRegion().equals("서울 은평구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 영등포구") && crime.getRegion().equals("서울 영등포구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 동작구") && crime.getRegion().equals("서울 동작구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 관악구") && crime.getRegion().equals("서울 관악구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 서초구") && crime.getRegion().equals("서울 서초구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 강남구") && crime.getRegion().equals("서울 강남구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 송파구") && crime.getRegion().equals("서울 송파구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 강동구") && crime.getRegion().equals("서울 강동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("서울특별시 종로구") && crime.getRegion().equals("서울 종로구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 중구") && crime.getRegion().equals("부산 중구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 서구") && crime.getRegion().equals("부산 서구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 동구") && crime.getRegion().equals("부산 동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 영도구") && crime.getRegion().equals("부산 영도구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 부산진구") && crime.getRegion().equals("부산 부산진구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 동래구") && crime.getRegion().equals("부산 동래구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 남구") && crime.getRegion().equals("부산 남구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 북구") && crime.getRegion().equals("부산 북구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 해운대구") && crime.getRegion().equals("부산 해운대구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 사하구") && crime.getRegion().equals("부산 사하구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 금정구") && crime.getRegion().equals("부산 금정구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 강서구") && crime.getRegion().equals("부산 강서구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 연제구") && crime.getRegion().equals("부산 연제구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 수영구") && crime.getRegion().equals("부산 수영구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("부산광역시 사상구") && crime.getRegion().equals("부산 사상구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대구광역시 중구") && crime.getRegion().equals("대구 중구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대구광역시 동구") && crime.getRegion().equals("대구 동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			}  else if(entry.getValue().equals("대구광역시 서구") && crime.getRegion().equals("대구 서구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대구광역시 남구") && crime.getRegion().equals("대구 남구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대구광역시 북구") && crime.getRegion().equals("대구 북구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대구광역시 수성구") && crime.getRegion().equals("대구 수성구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대구광역시 달서구") && crime.getRegion().equals("대구 달서구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("인천광역시 중구") && crime.getRegion().equals("인천 중구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("인천광역시 동구") && crime.getRegion().equals("인천 동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("인천광역시 서구") && crime.getRegion().equals("인천 서구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("인천광역시 미추홀구") && crime.getRegion().equals("인천 미추홀구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("인천광역시 연수구") && crime.getRegion().equals("인천 연수구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("인천광역시 남동구") && crime.getRegion().equals("인천 남동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("인천광역시 부평구") && crime.getRegion().equals("인천 부평구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("인천광역시 계양구") && crime.getRegion().equals("인천 계양구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("광주광역시 동구") && crime.getRegion().equals("광주 동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("광주광역시 서구") && crime.getRegion().equals("광주 서구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("광주광역시 남구") && crime.getRegion().equals("광주 남구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("광주광역시 북구") && crime.getRegion().equals("광주 북구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("광주광역시 광산구") && crime.getRegion().equals("광주 광산구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대전광역시 중구") && crime.getRegion().equals("대전 중구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대전광역시 동구") && crime.getRegion().equals("대전 동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대전광역시 서구") && crime.getRegion().equals("대전 서구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대전광역시 유성구") && crime.getRegion().equals("대전 유성구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("대전광역시 대덕구") && crime.getRegion().equals("대전 대덕구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("울산광역시 중구") && crime.getRegion().equals("울산 중구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("울산광역시 동구") && crime.getRegion().equals("울산 동구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("울산광역시 남구") && crime.getRegion().equals("울산 남구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} else if(entry.getValue().equals("울산광역시 북구") && crime.getRegion().equals("울산 북구")) {
				crimeService.updateCrime(new Crime(crime.getCrimeId(), 0, null, 0, Integer.parseInt(entry.getKey()), null));
			} 
		}
	}
	
}
