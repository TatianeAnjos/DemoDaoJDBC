package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Dao.DAOFactory;
import Model.entities.Department;

public class Program1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		System.out.println("-----Teste Department FindById-------");
		System.out.println("Digite o Id do departamento: ");
		int Id = sc.nextInt();
		Department dep = DAOFactory.createDepartmentDAO().FindById(Id);
		System.out.println(dep.toString());
		*/
		/*
		System.out.println("-----Teste Department FindAll-------");
		List <Department> list = new ArrayList();
		list = DAOFactory.createDepartmentDAO().FindAll();
		for(Department dep:list) {
			System.out.println(dep);
		}*/
		/*
		System.out.println("-----Teste Department DeleteById-------");
		System.out.println("Digite o Id do departamento que deseja deletar: ");
		int Id = sc.nextInt();
		DAOFactory.createDepartmentDAO().DeleteById(Id);
		*/
		/*
		System.out.println("-----Teste Department Insert-------");
		System.out.println("Digite o Id do departamento: ");
		int Id = sc.nextInt();
		System.out.println("Digite o nome do departamento: ");
		String name = sc.next();
		DAOFactory.createDepartmentDAO().Insert(new Department(Id,name));
		*/
		/*
		System.out.println("-----Teste Department Update-------");
		System.out.println("Digite o Id do departamento para atualizar: ");
		int Id = sc.nextInt();
		System.out.println("Digite o novo nome do departamento: ");
		String name = sc.next();
		DAOFactory.createDepartmentDAO().Update(new Department(Id,name));
		*/
	}
}
