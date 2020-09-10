package maven.hibernate.DAO;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import maven.hibernate.entities.Admin;

public class AdminDAO {
	
	public AdminDAO() {
		
	}
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Admin.class).buildSessionFactory();

	
	public void registerAdmin(Admin admin) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(admin);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	public void removeAdmin(Admin admin) {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		session.delete(admin);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	@SuppressWarnings("finally")
	public Admin getObj(String email) {
		Admin admin=null;
		try {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		admin = (Admin) session.createQuery("from Admin where email = '"+email+"'").getSingleResult();
		session.close();
		factory.close();
		}
		catch(NoResultException e){
			System.out.println(e.toString());
			admin=null;
		}
		finally {
		return admin;
		}
	}
}
