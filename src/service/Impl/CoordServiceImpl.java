package service.Impl;


import java.sql.SQLException;
import java.util.List;

import dao.CoordDao;
import dao.Impl.CoordDaoImpl;
import model.Coord;
import service.CoordService;

public class CoordServiceImpl implements CoordService {
	
	private CoordDao coordDao;

	public CoordServiceImpl() {
		coordDao = new CoordDaoImpl();
	}

	@Override
	public List<Coord> listCoord() throws SQLException {
		return coordDao.listCoord();
	}

	@Override
	public Coord getCoord(String coordId) throws SQLException {
		return coordDao.getCoord(coordId);
	}

	@Override
	public int insertCoord(Coord coord) throws SQLException {
		return coordDao.insertCoord(coord);
	}

	@Override
	public int updateCoord(Coord coord) throws SQLException {
		return coordDao.updateCoord(coord);
	}

	@Override
	public int deleteCoord(String coordId) throws SQLException {
		return coordDao.deleteCoord(coordId);
	}

	

}

