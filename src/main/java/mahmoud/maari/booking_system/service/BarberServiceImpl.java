package mahmoud.maari.booking_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.repository.BarberRepo;
import mahmoud.maari.booking_system.repository.BookingRepo;


@Service
@Transactional
public class BarberServiceImpl implements BarberService {

	
	private BarberRepo barberRepo;
	private BookingRepo bookingRepo;

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
	public List<Barber> findAll() {
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
	public boolean removeBarber(Barber barber) {
		 barberRepo.delete(barber);
		return barberRepo.existsById(barber.getId());
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.barberService#save(mahmoud.maari.booking_system.models.Barber)
	 */
	@Override
	public Barber save(Barber barber) {
		return barberRepo.save(barber);
	}
	
	
	
	

	@Override
	public boolean removeBarberFromBooking (Booking o,Barber b ) {
		List<Barber> barber = new ArrayList<>();
		o.setBarber(b=null);
		return barber.add(b);
	}
}
