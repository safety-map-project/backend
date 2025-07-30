package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.CctvDao;
import model.Cctv;
import util.ConnectionUtil;

public class CctvDaoImpl implements CctvDao {

	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;

	public CctvDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public List<Cctv> listCctv() throws SQLException {
		return null;
	}

	@Override
	public Cctv getCctv(int cctvId) throws SQLException {
		return null;
	}

	@Override
	public int insertCctv(Cctv cctv) throws SQLException {
		return 0;
	}

	@Override
	public int updateCctv(Cctv cctv) throws SQLException {
		return 0;
	}

	@Override
	public int deleteCctv(int cctvId) throws SQLException {
		return 0;
	}

}
