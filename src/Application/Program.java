package Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Model.Dao.DAOFactory;
import Model.entities.Department;
import Model.entities.Seller;

public class Program {
	

	public static void main (String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		
		/*
		System.out.println("-----Teste Seller FindByDepartment-------");
		System.out.println("Digite o Id do Department: ");
		int Id = sc.nextInt();
		List <Seller>list = new ArrayList();
		list = DAOFactory.createSellerDAO().FindByDepartment(new Department(Id,null));
		for(Seller seller : list) {
			System.out.println(seller);
		}*/
		
		/*
		System.out.println("-----Teste Seller FindById-------");
		System.out.println("Digite o Id do vendedor: ");
		int Id = sc.nextInt();
		Seller dep = DAOFactory.createSellerDAO().FindById(Id);
		System.out.println(dep.toString());
		*/
		
		/*
		System.out.println("-----Teste Seller FindAll-------");
		List <Seller> list = new ArrayList();
		list = DAOFactory.createSellerDAO().FindAll();
		for(Seller dep:list) {
			System.out.println(dep);
		}*/
		
		/*
		System.out.println("-----Teste seller DeleteById-------");
		System.out.println("Digite o Id do vendedor que deseja deletar: ");
		int Id = sc.nextInt();
		DAOFactory.createSellerDAO().DeleteById(Id);
		*/
		
		/*
		System.out.println("-----Teste Seller Insert-------");
		DAOFactory.createSellerDAO().Insert(new Seller(7,"João","joao.com",3000.00,sdf.parse("11/12/2005"),new Department(2,null)));
		*/
		
		/*
		System.out.println("-----Teste Seller Update-------");
		DAOFactory.createSellerDAO().Update(new Seller(1,"","",1500.00,sdf.parse("10/10/2010"),new Department(2,null)));
		*/
	}

}
