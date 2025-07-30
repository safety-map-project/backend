package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.CrimeDao;
import model.Crime;
import util.ConnectionUtil;

public class CrimeDaoImpl implements CrimeDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;
	
	public CrimeDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public List<Crime> listCrime() throws SQLException {
		return null;
	}

	@Override
	public Crime getCrime(int crimeId) throws SQLException {
		return null;
	}

	@Override
	public int insertCrime(Crime crime) throws SQLException {
		return 0;
	}

	@Override
	public int updateCrime(Crime crime) throws SQLException {
		return 0;
	}

	@Override
	public int deleteCrime(int crimeId) throws SQLException {
		return 0;
	}

}
