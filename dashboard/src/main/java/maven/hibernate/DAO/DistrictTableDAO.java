package maven.hibernate.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import maven.hibernate.entities.DistrictTable;

public class DistrictTableDAO {
	
	public DistrictTableDAO() {
		
	}
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DistrictTable.class).buildSessionFactory();


	public List<DistrictTable> getDistricts(String id){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<DistrictTable> districts = (List<DistrictTable>)session.createQuery("from DistrictTable where state_id = '"+id+"'").getResultList();
		session.close();
		factory.close();
		return districts;
	}
	
	public String getDistrictName(String id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		String name =((DistrictTable) session.createQuery("from DistrictTable where district_id = '"+id+"'").getSingleResult()).getDistrictName();
		session.close();
		factory.close();
		return name;
	}
	
	public DistrictTable getDistrictData(String id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		DistrictTable data=(DistrictTable) session.createQuery("from DistrictTable where district_id = '"+id+"'").getSingleResult();
		session.close();
		factory.close();
		return data;
	}
	public int getDistrictId(String district,String id){
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		int districtId=((DistrictTable)session.createQuery("from DistrictTable where district_name='"+district+"' and state_id='"+id+"'").getSingleResult()).getDistrictId();
		session.close();
		factory.close();
		return districtId;
	}
	
	public void updateDistrict(int active,int confirmed,int death,int recovered,int id) {
		Session session = factory.getCurrentSession();
		try {
		session.beginTransaction();
		DistrictTable obj=(DistrictTable)session.get(DistrictTable.class, id);
		obj.setActive(active);
		obj.setConfirmed(confirmed);
		obj.setDeath(death);
		obj.setRecovered(recovered);
		session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
		session.close();
		factory.close();
		}
		
	}
	
	public void createNewDistrict(DistrictTable district) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(district);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
}
