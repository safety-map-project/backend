package dao;

import java.sql.SQLException;
import java.util.List;

import model.Crime;
import model.CrimeAvg;

public interface CrimeDao {

	public abstract List<Crime> listCrime() throws SQLException;
	
	public abstract Crime getCrime(int regionId) throws SQLException;
	
	public abstract int insertCrime(Crime crime) throws SQLException;
	
	public abstract int updateCrime(Crime crime) throws SQLException;
	
	public abstract int deleteCrime(int crimeId) throws SQLException;
	
	public abstract CrimeAvg calculateCrimeAverage(List<Crime> cList) throws SQLException;
	
	public abstract List<Integer> getCrimeCount(int regionId) throws SQLException;
	
}
