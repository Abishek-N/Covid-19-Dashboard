package maven.hibernate.DAO;


import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import maven.hibernate.entities.Users;

public class UsersDAO {
	
	public UsersDAO() {
		
	}
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).buildSessionFactory();
	
	
	public void registerUser(Users user) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	public void removeUser(Users user) {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	@SuppressWarnings("finally")
	public Users getObj(String email) {
		Users user=null;
		try {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		user = (Users) session.createQuery("from Users where email = '"+email+"'").getSingleResult();
		session.close();
		factory.close();
		}
		catch(NoResultException e){
			System.out.println(e.toString());
		}
		finally {
		return user;
		}
	}
}
