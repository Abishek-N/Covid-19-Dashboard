package maven.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="state_data")
public class StateData {

	@Id
	@Column(name="state_id")
	int stateId;
	
	@Column(name="state_name")
	String stateName;
	
	@Column(name="state_code")
	String stateCode;
	
	@Column(name="confirmed")
	int confirmed;
	
	@Column(name="recovered")
	int recovered;
	
	@Column(name="active")
	int active;
	
	@Column(name="death")
	int death;

	public StateData() {
	}

	public StateData(String stateName, String stateCode, int confirmed, int recovered, int active, int death) {
		this.stateName = stateName;
		this.stateCode = stateCode;
		this.confirmed = confirmed;
		this.recovered = recovered;
		this.active = active;
		this.death = death;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
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
