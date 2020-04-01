package Model.Dao;

import java.util.List;

import Model.entities.Department;

public interface DepartmentDAO {

	void Insert(Department dpto);
	void Update(Department dpto);
	void DeleteById(Integer id);
	Department FindById(Integer id);
	List<Department> FindAll();
}

