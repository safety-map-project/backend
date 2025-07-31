package main;

import java.sql.SQLException;
import java.util.List;

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
			
			List<Crime> crimeList = CrimeAPI.getCrimeList(CrimeAPI.getCrimeAPI());
			for(Crime crime : crimeList) {
				crimeService.insertCrime(
						new Crime(0, crime.getCrimeYear(), 0, crime.getCrimeType(), crime.getCrimeCount(), crime.getRegion())
				);
			}
			
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	
	}
	
}
