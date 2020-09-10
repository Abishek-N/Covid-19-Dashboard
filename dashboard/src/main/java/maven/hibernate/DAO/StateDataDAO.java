package maven.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import maven.hibernate.entities.StateData;

public class StateDataDAO {
	
	public StateDataDAO(){
		
	}
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(StateData.class).buildSessionFactory();

	public List<StateData> getStates(){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<StateData> states =(List<StateData>) session.createQuery("from StateData").getResultList();
		session.close();
		factory.close();
		return states;
	}
	
	public String getStateName(String id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String name =((StateData) session.createQuery("from StateData where state_code = '"+id+"'").getSingleResult()).getStateName();
		session.close();
		factory.close();
		return name;
	}
	
	public void updateState(int active,int confirmed,int death,int recovered,String id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		StateData obj=(StateData)session.createQuery("from StateData where state_code = '"+id+"'").getSingleResult();
		obj.setActive(active);
		obj.setConfirmed(confirmed);
		obj.setDeath(death);
		obj.setRecovered(recovered);
		session.getTransaction().commit();
		session.close();
		factory.close();
		
	}
	
	
	public void createNewState(StateData state) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(state);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
}
