package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.CrimeDao;
import model.Crime;
import model.CrimeAvg;
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
				+ " from map.crime order by crimeId ";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<Crime> CrimeList = new ArrayList<Crime>();
		while (rs.next()) {
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
	public Crime getCrime(int regionId) throws SQLException {
		String sql = " select crimeId, crimeYear, regionId, crimeType, crimeCount, regionname "
				+ " from map.crime where regionId=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, regionId);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			Crime crime = new Crime();
			crime.setCrimeId(rs.getInt("crimeId"));
			crime.setCrimeYear(rs.getInt("crimeYear"));
			crime.setRegionId(rs.getInt("regionId"));
			crime.setCrimeType(rs.getString("crimeType"));
			crime.setCrimeCount(rs.getInt("crimeCount"));
			crime.setRegion(rs.getString("regionname"));
			return crime;
		}
		return null;
	}
	
	@Override
	public List<Integer> getCrimeCount(int regionId) throws SQLException {
		String sql = " select crimeCount from map.crime where regionId=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, regionId);
		rs = pstmt.executeQuery();
		List<Integer> crimeCounts = new ArrayList<Integer>();
		while(rs.next()) {
			crimeCounts.add(rs.getInt("crimeCount"));
		}
		return crimeCounts;
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

	
	
	// 평균 계산 메서드
	public CrimeAvg calculateCrimeAverage(List<Crime> cList) {
		List<Crime> crimeList = cList;
		CrimeAvg crimeAvg = new CrimeAvg();

		int seoulCrimeCount = 0, seoulGuCount = 0;
		int busanCrimeCount = 0, busanGuCount = 0;
		int daeguCrimeCount = 0, daeguGuCount = 0;
		int incheonCrimeCount = 0, incheonGuCount = 0;
		int gwangjuCrimeCount = 0, gwangjuGuCount = 0;
		int daejeonCrimeCount = 0, daejonGuCount = 0;
		int ulsanCrimeCount = 0, ulsanGuCount = 0;

		for (Crime c : crimeList) {
			if (c.getRegion().contains("서울")) { // 서울 전체 건수
				seoulCrimeCount += c.getCrimeCount();
				seoulGuCount++;
			} else if (c.getRegion().contains("부산")) { // 부산 전체 건수
				busanCrimeCount += c.getCrimeCount();
				busanGuCount++;
			} else if (c.getRegion().contains("대구")) { // 대구 전체 건수
				daeguCrimeCount += c.getCrimeCount();
				daeguGuCount++;
			} else if (c.getRegion().contains("인천")) { // 인천 전체 건수
				incheonCrimeCount += c.getCrimeCount();
				incheonGuCount++;
			} else if (c.getRegion().contains("광주")) { // 광주 전체 건수
				gwangjuCrimeCount += c.getCrimeCount();
				gwangjuGuCount++;
			} else if (c.getRegion().contains("대전")) { // 대전 전체 건수
				daejeonCrimeCount += c.getCrimeCount();
				daejonGuCount++;
			} else if (c.getRegion().contains("울산")) { // 울산 전체 건수
				ulsanCrimeCount += c.getCrimeCount();
				ulsanGuCount++;
			}
		}
		crimeAvg.setSeoulCrimeAvg(calculate(seoulCrimeCount, seoulGuCount / 2));
		crimeAvg.setBusanCrimeAvg(calculate(busanCrimeCount, busanGuCount / 2));
		crimeAvg.setDaeguCrimeAvg(calculate(daeguCrimeCount, daeguGuCount / 2));
		crimeAvg.setIncheonCrimeAvg(calculate(incheonCrimeCount, incheonGuCount / 2));
		crimeAvg.setGwangjuCrimeAvg(calculate(gwangjuCrimeCount, gwangjuGuCount / 2));
		crimeAvg.setDaejeonCrimeAvg(calculate(daejeonCrimeCount, daejonGuCount / 2));
		crimeAvg.setUlsanCrimeAvg(calculate(ulsanCrimeCount, ulsanGuCount / 2));

		return crimeAvg;
	}

	public double calculate(int total, int gu) {
		if (gu != 0) {
			return (double) total / gu;
		} else {
			return 0;
		}
	}

	

	

}
