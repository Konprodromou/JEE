package com.JPAMaven.JEE;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainApp {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JEE");

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Add objects to the 'customers' table of the DB.");
		
		addCustomer(1, "Kostis", "Prodromou");
		addCustomer(2, "Thomas", "Prodromou");
		addCustomer(3, "John", "Prodromou");
		addCustomer(4, "Chris", "Prodromou");

		ENTITY_MANAGER_FACTORY.close();

		
		
	}
	
	public static void addCustomer(int id, String firstName, String lastName) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			
			et = em.getTransaction();
			et.begin();
			Customer cust = new Customer();
			cust.setCust_ID(id);
			cust.setFirstName(firstName);
			cust.setLastName(lastName);
			em.persist(cust);
			et.commit();
			
		}
		
		catch(Exception ex) {
			
			if(et != null) {
				
				et.rollback();
			}
			ex.printStackTrace();
			
		}
		finally{
			em.close();
		}
		
	}

}
