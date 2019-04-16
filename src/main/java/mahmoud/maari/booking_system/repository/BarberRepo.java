package mahmoud.maari.booking_system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mahmoud.maari.booking_system.models.Barber;

public interface BarberRepo extends CrudRepository<Barber, Integer> {

	List<Barber> findByNameIgnoreCase(String name);
}
