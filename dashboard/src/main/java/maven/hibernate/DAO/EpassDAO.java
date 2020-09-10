package maven.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import maven.hibernate.entities.Epass;


public class EpassDAO {

	public EpassDAO(){
		
	}
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Epass.class).buildSessionFactory();

	public void applyEpass(Epass epass) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(epass);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	public void removePass(Epass epass) {
		try {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(epass);
		session.getTransaction().commit();
		session.close();
		factory.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public List<Epass> getPasses(){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Epass> passes = (List<Epass>)session.createQuery("from Epass").getResultList();
		session.close();
		factory.close();
		return passes;
	}
	
	@SuppressWarnings( "finally" )
	public Epass getPass(int id) {
		Epass epass=null;
		try {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		epass = (Epass)session.createQuery("from Epass where epass_id = '"+id+"'").getSingleResult();
		session.close();
		factory.close();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			return epass;
		}
	}
	
}
