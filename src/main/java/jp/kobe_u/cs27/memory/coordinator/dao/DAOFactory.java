package jp.kobe_u.cs27.memory.coordinator.dao;

public class DAOFactory {
	private static final int MONGO = 1;
	private static final int POSTGRESQL = 2;
	private DAOFactory instance;
	public DAOFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public DAOFactory getDAOFactory(int pattern){
		switch(pattern){
		case MONGO:
			break;
		case POSTGRESQL:
			break;
		default:
			return null;
		}
		return instance;
	}
}
