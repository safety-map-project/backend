package service.Impl;

import java.sql.SQLException;
import java.util.List;

import dao.RegionDao;
import dao.Impl.RegionDaoImpl;
import model.Region;
import service.RegionService;

public class RegionServiceImpl implements RegionService {

	private RegionDao regionDao;

	public RegionServiceImpl() {
		regionDao = new RegionDaoImpl();
	}

	@Override
	public List<Region> listRegion() throws SQLException {
		return regionDao.listRegion();
	}

	@Override
	public Region getRegion(int regionId) throws SQLException {
		return regionDao.getRegion(regionId);
	}

	@Override
	public int insertRegion(Region region) throws SQLException {
		return regionDao.insertRegion(region);
	}

	@Override
	public int updateRegion(Region region) throws SQLException {
		return regionDao.updateRegion(region);
	}

	@Override
	public int deleteRegion(int regionId) throws SQLException {
		return regionDao.deleteRegion(regionId);
	}

	

}
