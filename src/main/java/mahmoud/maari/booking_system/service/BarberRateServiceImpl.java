package mahmoud.maari.booking_system.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.BarberRate;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.repository.BarberRateRepo;

@Service
@Transactional
public class BarberRateServiceImpl implements BarberRateService {

	private BarberRateRepo rateRepo;

	
	@Autowired
	public BarberRateServiceImpl(BarberRateRepo rateRepo) {
		super();
		this.rateRepo = rateRepo;
	}
	
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.BarberRateService#findById(int)
	 */
	@Override
	public BarberRate findById(int id) {
		Optional<BarberRate> result= rateRepo.findById(id);
		
		return result.orElseThrow(IllegalArgumentException::new);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.BarberRateService#findAll()
	 */
	@Override
	public List<BarberRate> findAll() {
		return (List<BarberRate>) rateRepo.findAll();
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.BarberRateService#removeRate(int)
	 */
	@Override
	public boolean removeRate(int id) {
		rateRepo.deleteById(id);
		return rateRepo.existsById(id);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.BarberRateService#save(mahmoud.maari.booking_system.models.BarberRate)
	 */
	@Override
	public BarberRate save(BarberRate barberRate) {
		return rateRepo.save(barberRate);
	}
	
	@Override
	public boolean addRateToBooking(BarberRate rate,Booking booking) {
		List<BarberRate> r = new ArrayList<>();
		if(findById(rate.getId()).equals(booking.getRate())) {
			throw new IllegalArgumentException();
		}
		booking.setBookingRate(rate.getStarRate());
		booking.setRate(rate);
		return r.add(rate);
	}
	
}
