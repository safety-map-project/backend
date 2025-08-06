package service.Impl;

import java.sql.SQLException;
import java.util.List;

import dao.CrimeDao;
import dao.Impl.CrimeDaoImpl;
import model.Crime;
import model.CrimeAvg;
import service.CrimeService;

public class CrimeServiceImpl implements CrimeService {
	
	private CrimeDao crimeDao;
	
	public CrimeServiceImpl() {
		crimeDao = new CrimeDaoImpl();
	}

	@Override
	public List<Crime> listCrime() throws SQLException {
		return crimeDao.listCrime();
	}

	@Override
	public Crime getCrime(int regionId) throws SQLException {
		return crimeDao.getCrime(regionId);
	}

	@Override
	public int insertCrime(Crime crime) throws SQLException {
		return crimeDao.insertCrime(crime);
	}

	@Override
	public int updateCrime(Crime crime) throws SQLException {
		return crimeDao.updateCrime(crime);
	}

	@Override
	public int deleteCrime(int crimeId) throws SQLException {
		return crimeDao.deleteCrime(crimeId);
	}

	@Override
	public CrimeAvg calculateCrimeAverage(List<Crime> cList) throws SQLException {
		// TODO Auto-generated method stub
		return crimeDao.calculateCrimeAverage(cList);
	}

	@Override
	public List<Integer> getCrimeCount(int regionId) throws SQLException {
		return crimeDao.getCrimeCount(regionId);
	}

	
	
}
