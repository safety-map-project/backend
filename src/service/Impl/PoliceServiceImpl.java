package service.Impl;

import java.sql.SQLException;
import java.util.List;

import dao.PoliceDao;
import dao.Impl.PoliceDaoImpl;
import model.Police;
import service.PoliceService;

public class PoliceServiceImpl implements PoliceService{

	private PoliceDao policeDao;
	
	public PoliceServiceImpl() {
		policeDao = new PoliceDaoImpl();
	}

	@Override
	public List<Police> listPolice() throws SQLException {
		return policeDao.listPolice();
	}

	@Override
	public Police getPolice(int policeId) throws SQLException {
		return policeDao.getPolice(policeId);
	}

	@Override
	public int insertPolice(Police police) throws SQLException {
		return policeDao.insertPolice(police);
	}

	@Override
	public int updatePolice(Police police) throws SQLException {
		return policeDao.updatePolice(police);
	}

	@Override
	public int deletePolice(int policeId) throws SQLException {
		return policeDao.deletePolice(policeId);
	}
	
	
	
}
