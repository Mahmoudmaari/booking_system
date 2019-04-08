package mahmoud.maari.booking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.repository.BarberRepo;

@Service
@Transactional
public class BarberServiceImpl implements barberService {

	
	private BarberRepo barberRepo;

	@Autowired
	public BarberServiceImpl(BarberRepo barberRepo) {
		super();
		this.barberRepo = barberRepo;
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.barberService#findById(int)
	 */
	@Override
	public Barber findById(int id) {
		Optional<Barber> result = barberRepo.findById(id);
		
		return result.orElseThrow(IllegalArgumentException::new);
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.barberService#findall()
	 */
	@Override
	public List<Barber> findall() {
		return (List<Barber>) barberRepo.findAll();
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.barberService#findByName(java.lang.String)
	 */
	@Override
	public List<Barber> findByName(String name){
		
		return	barberRepo.findByNameIgnoreCase(name);
		
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.barberService#removeProduct(int)
	 */
	@Override
	public boolean removeBarber(int id) {
		barberRepo.deleteById(id);
		return barberRepo.existsById(id);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.barberService#save(mahmoud.maari.booking_system.models.Barber)
	 */
	@Override
	public Barber save(Barber barber) {
		return barberRepo.save(barber);
	}
	
}
