package mahmoud.maari.booking_system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mahmoud.maari.booking_system.models.Client;



public interface ClientRepo extends CrudRepository<Client, Integer>{

	List<Client> findByclientNameIgnoreCase(String name);
	
}
