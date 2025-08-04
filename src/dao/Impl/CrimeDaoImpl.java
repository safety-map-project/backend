package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CrimeDao;
import model.Crime;
import model.Region;
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
		String sql = " select crimeId, crimeYear, regionId, crimeType, crimeCount, regionname "
				+ " from crime order by crimeId ";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<Crime> CrimeList = new ArrayList<Crime>();
		while(rs.next()) {
			Crime crime = new Crime();
			crime.setCrimeId(rs.getInt("crimeId"));
			crime.setCrimeYear(rs.getInt("crimeYear"));
			crime.setRegionId(rs.getInt("regionId"));
			crime.setCrimeType(rs.getString("crimeType"));
			crime.setCrimeCount(rs.getInt("crimeCount"));
			crime.setRegion(rs.getString("regionname"));
			CrimeList.add(crime);
		}
		return CrimeList;
	}

	@Override
	public Crime getCrime(int crimeId) throws SQLException {
		return null;
	}

	@Override
	public int insertCrime(Crime crime) throws SQLException {
		
		// crimeId, crimeYear, regionId, crimeType, crimeCount, region
		String insertSql = " insert into crime(crimeid, crimeyear, crimetype, crimecount, regionid, regionname) "
				+ " values(seq_crime.nextval, ?, ?, ?, 29155, ?) ";
		pstmt = conn.prepareStatement(insertSql);
		
		pstmt.setInt(1, crime.getCrimeYear());
		pstmt.setString(2, crime.getCrimeType());
		pstmt.setInt(3, crime.getCrimeCount());
//		pstmt.setInt(4, regionidList.get());
		pstmt.setString(4, crime.getRegion());
		
		return pstmt.executeUpdate();
	}

	@Override
	public int updateCrime(Crime crime) throws SQLException {
		
		String updateRegionIdsql = " update crime set regionid=? where crimeid=? ";
		pstmt = conn.prepareStatement(updateRegionIdsql);
		pstmt.setInt(1, crime.getRegionId());
		pstmt.setInt(2, crime.getCrimeId());
		
		return pstmt.executeUpdate();
	}

	@Override
	public int deleteCrime(int crimeId) throws SQLException {
		return 0;
	}

}
