package service.Impl;

import java.sql.SQLException;
import java.util.List;

import dao.CrimeDao;
import dao.Impl.CrimeDaoImpl;
import model.Crime;
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
	public Crime getCrime(int crimeId) throws SQLException {
		return crimeDao.getCrime(crimeId);
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

}
