package mahmoud.maari.booking_system.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.bind.annotation.GetMapping;

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
	private boolean booked;

	
	
	
	public Booking(LocalDate bookingDate, LocalTime bookingTime, boolean booked) {
		super();
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.booked = booked;
	}


	public Booking() {} 
	
	


	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	


	public LocalTime getBookingTime() {
		return bookingTime;
	}


	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
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
}
