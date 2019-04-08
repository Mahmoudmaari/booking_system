package mahmoud.maari.booking_system.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mahmoud.maari.booking_system.models.Booking;

public interface BookingRepo extends CrudRepository<Booking, Integer>{

	List<Booking> findBybookingDate(LocalDate date);
	List<Booking> findBybookingTime(LocalTime time);
	
}
