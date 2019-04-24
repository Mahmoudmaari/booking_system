package mahmoud.maari.booking_system.service;

import java.util.List;

import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.BarberRate;
import mahmoud.maari.booking_system.models.Booking;

public interface BarberService {

	Barber findById(int id);

	List<Barber> findAll();

	List<Barber> findByName(String name);

	boolean removeBarber(Barber barber);

	Barber save(Barber barber);

	boolean addRateToBarber(Barber b, BarberRate r);

	boolean addBookingToBarber(Booking o, Barber b);

	boolean removeBarberFromBooking(Booking o, Barber b);

	
}