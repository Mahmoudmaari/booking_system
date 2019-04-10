package mahmoud.maari.booking_system.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {
	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingDate=" + bookingDate + ", Bookningtime=" + bookingTime + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate bookingDate;
	private LocalTime bookingTime;

	
	public Booking(LocalDate bookingDate, LocalTime bookningtime) {
		super();
		this.bookingDate = bookingDate;
		bookingTime = bookningtime;
	}
	
	public Booking() {} 
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalTime getBookningtime() {
		return bookingTime;
	}

	public void setBookningtime(LocalTime bookningtime) {
		bookingTime = bookningtime;
	}



	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingTime == null) ? 0 : bookingTime.hashCode());
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
		if (bookingTime == null) {
			if (other.bookingTime != null)
				return false;
		} else if (!bookingTime.equals(other.bookingTime))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	public Boolean addClient(Client c) {
		List<Client> client= new ArrayList<>();
		if(c.getBooking()!=null) {
			throw new IllegalArgumentException();
		}
		c.setBooking(this);
		return client.add(c);
	}

}
