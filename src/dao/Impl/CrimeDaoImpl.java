package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.CrimeDao;
import model.Crime;
import util.ConnectionUtil;
import util.APIUtil.CrimeAPI;

public class CrimeDaoImpl implements CrimeDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;
	private List<Crime> crimeList = CrimeAPI.getCrimeList(CrimeAPI.getCrimeAPI());
	
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
		
		// crimeId, crimeYear, regionId, crimeType, crimeCount, region
		String sql = " insert into crime values(seq_crime.nextval, ?, ?, ?, ?, ?) ";
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, crime.getCrimeYear());
		pstmt.setInt(2, crime.getRegionId());
		pstmt.setString(3, crime.getCrimeType());
		pstmt.setInt(4, crime.getCrimeCount());
		pstmt.setString(5, crime.getRegion());			
		
		return pstmt.executeUpdate();
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
