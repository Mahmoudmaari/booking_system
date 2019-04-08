package mahmoud.maari.booking_system.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mahmoud.maari.booking_system.models.HaircutStyle;

public interface HaircutStyleRepo extends CrudRepository<HaircutStyle, Integer>{

	List<HaircutStyle> findByhaircutTypeIgnoreCase(String name);
	
}
