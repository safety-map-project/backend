package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.PoliceDao;
import model.Police;
import util.ConnectionUtil;

public class PoliceDaoImpl implements PoliceDao {

	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;

	public PoliceDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public List<Police> listPolice() throws SQLException {
		return null;
	}

	@Override
	public Police getPolice(int policeId) throws SQLException {
		return null;
	}

	@Override
	public int insertPolice(Police police) throws SQLException {
		return 0;
	}

	@Override
	public int updatePolice(Police police) throws SQLException {
		return 0;
	}

	@Override
	public int deletePolice(int policeId) throws SQLException {
		return 0;
	}

}
