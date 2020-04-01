package Model.Dao;

import java.util.List;

import Model.entities.Department;
import Model.entities.Seller;

public interface SellerDAO {

	void Insert(Seller sl);
	void Update(Seller sl);
	void DeleteById(Integer id);
	Seller FindById(Integer id);
	List<Seller> FindAll();
	List<Seller> FindByDepartment(Department department);
}

