package mahmoud.maari.booking_system.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mahmoud.maari.booking_system.models.Booking;

public interface BookingRepo extends CrudRepository<Booking, Integer>{
	
	List<Booking> findBybookingDate( LocalDate date);
	List<Booking> findBybookingTime(LocalTime time);
	
	@Query("SELECT b FROM Booking b WHERE b.barber.id = :id ")
	List<Booking> findBarber(@Param("id") int id);
}
