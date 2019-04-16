package mahmoud.maari.booking_system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mahmoud.maari.booking_system.models.ClientC;



public interface ClientRepo extends CrudRepository<ClientC, Integer>{

	List<ClientC> findByclientNameIgnoreCase(String name);
	
}
