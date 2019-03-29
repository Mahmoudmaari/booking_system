package mahmoud.maari.booking_system.repository;

import org.springframework.data.repository.CrudRepository;

import mahmoud.maari.booking_system.models.Booking;

public interface BookingRepo extends CrudRepository<Booking, Integer>{

}
