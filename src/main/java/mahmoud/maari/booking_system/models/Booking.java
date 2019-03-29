package mahmoud.maari.booking_system.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Booking {
	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingDate=" + bookingDate + ", Bookningtime=" + Bookningtime + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate bookingDate;
	private LocalTime Bookningtime;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private Barber barber;
	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER,mappedBy="booking")
	private Client client;

	public Booking(LocalDate bookingDate, LocalTime bookningtime) {
		super();
		this.bookingDate = bookingDate;
		Bookningtime = bookningtime;
	}
	
	public Booking() {} 
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalTime getBookningtime() {
		return Bookningtime;
	}

	public void setBookningtime(LocalTime bookningtime) {
		Bookningtime = bookningtime;
	}

	public Barber getBarber() {
		return barber;
	}

	public void setBarber(Barber barber) {
		this.barber = barber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Bookningtime == null) ? 0 : Bookningtime.hashCode());
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
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
		Booking other = (Booking) obj;
		if (Bookningtime == null) {
			if (other.Bookningtime != null)
				return false;
		} else if (!Bookningtime.equals(other.Bookningtime))
			return false;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
