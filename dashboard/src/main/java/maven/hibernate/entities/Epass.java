package maven.hibernate.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="epass")
public class Epass {
	
	@Id
	@Column(name="epass_id")
	int epassId;
	
	@Column(name="name")
	String name;
	
	@Column(name="age")
	int age;
	
	@Column(name="email")
	String email;
	
	@Column(name="address")
	String address;
	
	@Column(name="aadhar_no")
	String aadharNo;
	
	@Column(name="epass_purpose")
	String purpose;
	
	@Column(name="epass_from")
	String from;
	
	@Column(name="epass_to")
	String to;
	
	public Epass() {
		
	}

	public Epass(String name, int age, String email, String address, String aadharNo, String purpose, String from, String to) {
		this.name = name;
		this.age = age;
		this.email=email;
		this.address = address;
		this.aadharNo = aadharNo;
		this.purpose = purpose;
		this.from = from;
		this.to = to;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEpassId() {
		return epassId;
	}

	public void setEpassId(int epassId) {
		this.epassId = epassId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	
	
}
