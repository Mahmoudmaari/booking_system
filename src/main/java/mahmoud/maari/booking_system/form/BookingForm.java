package mahmoud.maari.booking_system.form;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingForm {

	private LocalDate bookingDate;
	private LocalTime bookingTime;
	private boolean booked;
	
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
	public boolean isBooked() {
		return booked;
	}
	public void setBooked(boolean booked) {
		this.booked = booked;
	}

}
