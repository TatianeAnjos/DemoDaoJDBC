package Model.Dao;

import Model.Dao.Implementation.DepartmentDAOJDBC;
import Model.Dao.Implementation.SellerDAOJDBC;
import db.DB;

public class DAOFactory {
	
	public static SellerDAO createSellerDAO() {
		return new SellerDAOJDBC(DB.getConnection());
	}
	public static DepartmentDAO createDepartmentDAO() {
		return new DepartmentDAOJDBC(DB.getConnection());
	}
}
