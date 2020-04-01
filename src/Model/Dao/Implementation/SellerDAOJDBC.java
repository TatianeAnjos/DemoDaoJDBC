package Model.Dao.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.Dao.SellerDAO;
import Model.entities.Department;
import Model.entities.Seller;
import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class SellerDAOJDBC implements SellerDAO{

	private Connection conn;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public SellerDAOJDBC(Connection c) {
		this.conn = c;
	}
	
	@Override
	public void Insert(Seller sl) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO SELLER"
					+ "(IDSELLER, SELLERNAME, EMAIL, SALARYBASE, BIRTHDATE, DEPARTMENT)"
					+ " VALUES (?,?,?,?,?,?)");
			ps.setInt(1,sl.getId());
			ps.setString(2,sl.getName());
			ps.setString(3,sl.getEmail());
			ps.setDouble(4,sl.getBaseSalary());
			ps.setDate(5,new java.sql.Date(sl.getBirthDate().getTime()));
			ps.setInt(6, sl.getDepartment().getId());
			
			int linhasAfetadas = ps.executeUpdate();
			System.out.println("linhas afetadas :" + linhasAfetadas);
			
		}catch(SQLException e){
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void Update(Seller sl) {		
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"UPDATE SELLER "
				    +"SET SELLERNAME = NVL(?,SELLERNAME),EMAIL = NVL (?,EMAIL), SALARYBASE = NVL (?,SALARYBASE),BIRTHDATE = NVL (?,BIRTHDATE), DEPARTMENT = NVL (?,DEPARTMENT)"
				    + "WHERE IDSELLER = ? ");
		
			ps.setString(1,sl.getName());
			ps.setString(2,sl.getEmail());
			ps.setDouble(3,sl.getBaseSalary());
			ps.setDate(4,new java.sql.Date(sl.getBirthDate().getTime()));
			ps.setInt(5, sl.getDepartment().getId());
			ps.setInt(6, sl.getId());
			
			int linhasAfetadas = ps.executeUpdate();
			System.out.println("linhas afetadas :" + linhasAfetadas);
			
		}catch(SQLException e){
			e.printStackTrace();
		//}catch(ParseException e){
		//	e.printStackTrace();
		}finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public void DeleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM SELLER WHERE IDSELLER = ?");
			ps.setInt(1, id);
			
			int linhasAfetadas = ps.executeUpdate();
			if (linhasAfetadas > 0) {
				System.out.println("Sucesso! Linhas afetadas :" + linhasAfetadas);
			}else {
				System.out.println("O ID informado não existe!");
			}
		}catch(SQLException e){
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Seller FindById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.departmentName as DepName "
					+ "FROM seller INNER JOIN department  "
					+ "ON seller.department = department.departmentId  "
					+ "WHERE seller.idSeller = ?" );
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("department"));
				dep.setName(rs.getString("DepName"));
				Seller sel = new Seller();
				sel.setId(rs.getInt("idSeller"));
				sel.setName(rs.getString("sellerName"));
				sel.setEmail(rs.getString("email"));
				sel.setBaseSalary(rs.getDouble("salaryBase"));
				sel.setBirthDate(rs.getDate("birthDate"));
				sel.setDepartment(dep);
				return sel;
			}else {
				return null;
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> FindAll() {
		List <Seller> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement(
					"SELECT seller.*,department.departmentName as DepName "
					+ "FROM seller INNER JOIN department " 
					+ "ON seller.IdSeller = department.departmentId ORDER BY seller.sellerName ");
			rs = ps.executeQuery();
			while(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("department"));
				dep.setName(rs.getString("DepName"));
				Seller sel = new Seller();
				sel.setId(rs.getInt("idSeller"));
				sel.setName(rs.getString("sellerName"));
				sel.setEmail(rs.getString("email"));
				sel.setBaseSalary(rs.getDouble("salaryBase"));
				sel.setBirthDate(rs.getDate("birthDate"));
				sel.setDepartment(dep);
				list.add(sel);
			}
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> FindByDepartment(Department department) {
		List <Seller> list = new ArrayList();
		HashMap<Integer, Department> depto = new HashMap<Integer, Department>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement(	
					"SELECT seller.*,department.departmentName as DepName  "
					+ "FROM seller INNER JOIN department  "
					+ "ON seller.department = department.departmentId "
					+ "WHERE DepartmentId = ? ORDER BY sellerName ");
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Department dep = depto.get(rs.getInt("department"));
				
				if(dep == null) {
					dep = new Department(rs.getInt("department"),rs.getString("DepName"));
					depto.put(rs.getInt("department"), dep);
				}
				
				Seller sel = new Seller();
				sel.setId(rs.getInt("idSeller"));
				sel.setName(rs.getString("sellerName"));
				sel.setEmail(rs.getString("email"));
				sel.setBaseSalary(rs.getDouble("salaryBase"));
				sel.setBirthDate(rs.getDate("birthDate"));
				sel.setDepartment(dep);
				
				list.add(sel);
								
			}
			
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
}
