package mahmoud.maari.booking_system.service;

import java.time.LocalDate;
import java.util.List;

import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.ClientC;

public interface BookingService {

	Booking findById(int id);

	List<Booking> findAll();

	boolean removeBooking(int id);

	Booking save(Booking booking);

	List<Booking> findBookingByDate(LocalDate date);




}