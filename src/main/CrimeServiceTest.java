package main;

import java.sql.SQLException;

import service.CrimeService;
import service.Impl.CrimeServiceImpl;
import util.APIUtil.CrimeAPI;

public class CrimeServiceTest {

	public static void main(String[] args) {
	
		CrimeService crimeService = new CrimeServiceImpl();
		
		try {
			
			
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	
	}
	
}
