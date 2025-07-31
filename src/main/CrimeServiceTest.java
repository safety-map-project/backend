package main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			
			Map<String, String> regionMap = new HashMap();
			List<Region> regionList = regionService.listRegion();
			for(Region region : regionList) {
				regionMap.put(region.getRegionId(), region.getGu());
			}
			
			System.out.println(regionMap);
			
//			List<Crime> crimeList = CrimeAPI.getCrimeList(CrimeAPI.getCrimeAPI());
//			for(Crime crime : crimeList) {
//				crimeService.insertCrime(
//						new Crime(0, crime.getCrimeYear(), 0, crime.getCrimeType(), crime.getCrimeCount(), crime.getRegion())
//				);
//			}
			
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	
	}
	
}
