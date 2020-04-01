package Model.Dao.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Dao.DepartmentDAO;
import Model.entities.Department;
import db.DB;
import db.DbException;

public class DepartmentDAOJDBC implements DepartmentDAO{

	private Connection conn;
	
	public DepartmentDAOJDBC(Connection c) {
		this.conn = c;
	}
	
	@Override
	public void Insert(Department dpto) {
		PreparedStatement ps = null ;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("INSERT INTO DEPARTMENT VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS );
			
			ps.setInt(1,dpto.getId());
			ps.setString(2, dpto.getName());
			
			int linhasAfetadas = ps.executeUpdate();
			if(linhasAfetadas > 0) {
				System.out.println("Inserido com sucesso!");
			}else {
				System.out.println("Algo errado! Nenhuma linha foi afetada.");
			}
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void Update(Department dpto) {
		PreparedStatement ps = null ;
		try {
			ps = conn.prepareStatement("UPDATE DEPARTMENT "
					+ "SET departmentName = ? "
					+ "WHERE DEPARTMENTID = ?");
			ps.setString(1, dpto.getName());
			ps.setInt(2, dpto.getId());
			
			int linhasAfetadas = ps.executeUpdate();
			if(linhasAfetadas > 0) {
				System.out.println("Atualizado com sucesso!");
			}else {
				System.out.println("Algo errado! Nenhuma linha foi afetada.");
			}
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void DeleteById(Integer id) {
		PreparedStatement ps = null ;
		try {
			ps = conn.prepareStatement("DELETE FROM DEPARTMENT"
					+ " WHERE DEPARTMENTID = ?");
			ps.setInt(1,id);
			
			int linhasAfetadas = ps.executeUpdate();
			
			if(linhasAfetadas > 0) {
				System.out.println("Deletado com sucesso! Linhas afetadas = "+linhasAfetadas);
			}else {
				System.out.println("Algo errado! Nenhuma linha foi afetada.");
			}
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Department FindById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = conn.prepareStatement("SELECT DEPARTMENT.* FROM DEPARTMENT WHERE DEPARTMENTID = ?");
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("departmentId"));
				dep.setName(rs.getString("departmentName"));
				return dep;
			}
			return null;
		}catch(SQLException e) {
			e.getMessage();
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return null;
	}

	@Override
	public List<Department> FindAll() {
		List <Department> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(" select department.* from department ");
			rs = ps.executeQuery();
			while(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("departmentId"));
				dep.setName(rs.getString("departmentName"));
				list.add(dep);
			}
			return list;
		}catch(SQLException e) {
			e.getMessage();
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		return null;
	}

}
