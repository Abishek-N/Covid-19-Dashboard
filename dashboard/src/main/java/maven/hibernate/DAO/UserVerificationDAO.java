package maven.hibernate.DAO;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import maven.hibernate.entities.UserVerification;

public class UserVerificationDAO {
	
	public UserVerificationDAO(){
		
	}
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserVerification.class).buildSessionFactory();
	
	public void insertOtp(UserVerification user) {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	public void removeOtp(UserVerification user) {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	@SuppressWarnings("finally")
	public UserVerification getObj(String username) {
		UserVerification user=null;
		try {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		user = (UserVerification) session.createQuery("from UserVerification where username = '"+username+"'").getSingleResult();
		session.close();
		factory.close();
		return user;
	}
		catch(NoResultException e) {
			System.out.println(e.toString());
			user=null;
		}
		finally {
		return user;
		}
}
	@SuppressWarnings("finally")
	public UserVerification checkOtp(int otp) {
		UserVerification user=null;
		try {
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		user = (UserVerification) session.createQuery("from UserVerification where otp = '"+otp+"'").getSingleResult();
		session.close();
		return user;
	}
		catch(NoResultException e) {
			System.out.println(e.toString());
			user=null;
		}
		finally {
		return user;
		}
	}

}
