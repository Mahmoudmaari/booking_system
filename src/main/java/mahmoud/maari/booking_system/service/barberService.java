package mahmoud.maari.booking_system.service;

import java.util.List;

import mahmoud.maari.booking_system.models.Barber;

public interface barberService {

	Barber findById(int id);

	List<Barber> findall();

	List<Barber> findByName(String name);

	boolean removeBarber(int id);

	Barber save(Barber barber);

}