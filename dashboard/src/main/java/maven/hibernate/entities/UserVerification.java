package maven.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_verification")
public class UserVerification {
	
	@Id
	@Column(name="username")
	String username;
	
	@Column(name="password")
	String password;
	
	@Column(name="email")
	String email;
	
	@Column(name="otp")
	int otp;

	public UserVerification(String username, String password, String email, int otp) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.otp = otp;
	}

	public UserVerification() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	
}
