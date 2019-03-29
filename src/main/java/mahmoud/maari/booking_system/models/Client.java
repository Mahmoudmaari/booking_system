package mahmoud.maari.booking_system.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Entity
	public class Client {


		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private String clientName;
		private LocalDate birthDate;
		private String sex;
		private String phoneNumber;
		@Email
		private String email;
		
		@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
				fetch=FetchType.EAGER)
		private Barber barber;
		@OneToOne
		(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
		fetch=FetchType.EAGER)
		private Booking booking;
		@ManyToOne
		(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
		fetch=FetchType.EAGER)
		private HaircutStyle haircutStyle;
		
		
		
		
		public Client(String clientName, LocalDate birthDate, String sex, String phoneNumber, @Email String email) {
			super();
			this.clientName = clientName;
			this.birthDate = birthDate;
			this.sex = sex;
			this.phoneNumber = phoneNumber;
			this.email = email;
		}
		
		public Client() {}
		
		
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
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
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

		public void setBooking(Booking booking) {
			this.booking = booking;
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

		
		@Override
		public String toString() {
			return "Client [id=" + id + ", clientName=" + clientName + ", birthDate=" + birthDate + ", sex=" + sex
					+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", haircutStyle=" + haircutStyle + "]";
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

