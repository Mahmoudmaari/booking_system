package mahmoud.maari.booking_system.service;

import java.util.List;

import mahmoud.maari.booking_system.models.Booking;

public interface BookingService {

	Booking findById(int id);

	List<Booking> findAll();

	

	boolean removeBooking(int id);

	Booking save(Booking booking);

}