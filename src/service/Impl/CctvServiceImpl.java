package service.Impl;

import java.sql.SQLException;
import java.util.List;

import dao.CctvDao;
import dao.Impl.CctvDaoImpl;
import model.Cctv;
import service.CctvService;

public class CctvServiceImpl implements CctvService {

	private CctvDao cctvDao;
	
	public CctvServiceImpl() {
		cctvDao = new CctvDaoImpl();
	}

	@Override
	public List<Cctv> listCctv() throws SQLException {
		return cctvDao.listCctv();
	}

	@Override
	public Cctv getCctv(int cctvId) throws SQLException {
		return cctvDao.getCctv(cctvId);
	}

	@Override
	public int insertCctv(Cctv cctv) throws SQLException {
		return cctvDao.insertCctv(cctv);
	}

	@Override
	public int updateCctv(Cctv cctv) throws SQLException {
		return cctvDao.updateCctv(cctv);
	}

	@Override
	public int deleteCctv(int cctvId) throws SQLException {
		return cctvDao.deleteCctv(cctvId);
	}

}
