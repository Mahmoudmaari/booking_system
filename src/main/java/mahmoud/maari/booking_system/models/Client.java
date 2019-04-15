package mahmoud.maari.booking_system.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String clientName;
	private LocalDate birthDate;
	private String gender;
	private String phoneNumber;
	@Email
	private String email;
	private String password;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private Barber barber;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private Booking booking;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private HaircutStyle haircutStyle;

	public Client(String clientName, LocalDate birthDate, String gender, String phoneNumber, @Email String email,
			String password) {
		super();
		this.clientName = clientName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}

	public Client() {
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return gender;
	}

	public void setSex(String sex) {
		this.gender = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Barber getBarber() {
		return barber;
	}
 
	public void setBarber(Barber barber) {
		this.barber = barber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Booking getBooking() {
		return booking;
	}

	public Booking setBooking(Booking booking) {
		return this.booking = booking;
	}

	public HaircutStyle getHaircutStyle() {
		return haircutStyle;
	}

	public void setHaircutStyle(HaircutStyle haircutStyle) {
		this.haircutStyle = haircutStyle;
	}

	public int getId() {
		return id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", clientName=" + clientName + ", birthDate=" + birthDate + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password + ", barber=" + barber
				+ ", booking=" + booking + ", haircutStyle=" + haircutStyle + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
