package com.JPAMaven.JEE;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class MainApp {

	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JEE");

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.out.println("Add objects to the 'customers' table of the DB.");
		
//		addCustomer(1, "Kostis", "Prodromou");
//		addCustomer(2, "Thomas", "Prodromou");
//		addCustomer(3, "John", "Prodromou");
//		addCustomer(4, "Chris", "Prodromou");
		
		//getCustomer(3);

		//getCustomers();
		
		changeFirstName(1, "Panos");
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
	
	public static void getCustomer(int id) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM customer c WHERE c.id = :cust_ID";
        TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
        tq.setParameter("cust_ID", id);
        Customer cust = null;
        
        try {
        	
        	cust = tq.getSingleResult();
        	System.out.println(cust.getFirstName() + " " + cust.getLastName());
        	
        }
        catch(NoResultException ex){
        	System.out.println("No results found into DB");
        	ex.printStackTrace();
        }
        finally {
        	
        	em.close();
        }
		
	}
	
	public static void getCustomers() {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM customer c WHERE c.id is NOT NULL";
        TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
        List<Customer> custs;
        
        try {
        	
        	custs = tq.getResultList();
        	System.out.println(custs);
        	
        }
        catch(NoResultException ex){
        	System.out.println("No results found into DB");
        	ex.printStackTrace();
        }
        finally {
        	
        	em.close();
        }
		
	}
	
	public static void changeFirstName(int id, String firstName) {
		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer cust = null;
		try {
			
			et = em.getTransaction();
			et.begin();
		
			cust = em.find(Customer.class, id);
			cust.setFirstName(firstName); // the parameter of the function
			
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
