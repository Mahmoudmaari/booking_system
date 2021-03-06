package mahmoud.maari.booking_system.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.HaircutStyle;

public interface BookingService {

	Booking findById(int id);

	List<Booking> findAll();

	boolean removeBooking(int id);

	Booking save(Booking booking);

	List<Booking> findBookingByDate(LocalDate date);

	List<Booking> findBookingByBarberId(Barber barber);

	BigDecimal RateCal(Barber b);

	Booking addBarberToBooking(Booking booking, Barber barber);

	Booking addHaircutToBooking(Booking booking, HaircutStyle haircut);

	




}