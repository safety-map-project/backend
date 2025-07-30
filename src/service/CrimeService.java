package service;

import java.sql.SQLException;
import java.util.List;

import model.Crime;

public interface CrimeService {

	public abstract List<Crime> listCrime() throws SQLException;

	public abstract Crime getCrime(int crimeId) throws SQLException;

	public abstract int insertCrime(Crime crime) throws SQLException;

	public abstract int updateCrime(Crime crime) throws SQLException;

	public abstract int deleteCrime(int crimeId) throws SQLException;

}
