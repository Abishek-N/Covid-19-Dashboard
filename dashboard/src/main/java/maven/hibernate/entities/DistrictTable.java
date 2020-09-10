package maven.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="district_table")
public class DistrictTable {

	@Id
	@Column(name="district_id")
	int districtId;
	
	@Column(name="district_name")
	String districtName;
	
	@Column(name="state_id")
	String stateId;
	
	@Column(name="confirmed")
	int confirmed;
	
	@Column(name="recovered")
	int recovered;
	
	@Column(name="active")
	int active;
	
	@Column(name="death")
	int death;
	

	public DistrictTable() {

	}

	public DistrictTable(String districtName, String stateId, int confirmed, int recovered, int active, int death) {
		this.districtName = districtName;
		this.stateId = stateId;
		this.confirmed = confirmed;
		this.recovered = recovered;
		this.active = active;
		this.death = death;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getDeath() {
		return death;
	}

	public void setDeath(int death) {
		this.death = death;
	}
	
	
}
