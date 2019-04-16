package mahmoud.maari.booking_system.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.ClientC;
import mahmoud.maari.booking_system.repository.BookingRepo;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private BookingRepo bookingRepo;
	
	
	@Autowired
	public BookingServiceImpl(BookingRepo bookingRepo) {
		super();
		this.bookingRepo = bookingRepo;
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.BookingService#findById(int)
	 */
	@Override
	public Booking findById(int id) {
		Optional<Booking> result = bookingRepo.findById(id);
		
		return result.orElseThrow(IllegalArgumentException::new);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.BookingService#findAll()
	 */
	@Override
	public List<Booking> findAll() {
		
		return (List<Booking>) bookingRepo.findAll();
		
	}
	@Override
	public List<Booking> findBookingByDate(LocalDate date){
		return bookingRepo.findBybookingDate(date);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.BookingService#removeBooking(int)
	 */
	@Override
	public boolean removeBooking(int id) {
		bookingRepo.deleteById(id);
		return bookingRepo.existsById(id);
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.BookingService#save(mahmoud.maari.booking_system.models.Booking)
	 */
	@Override
	public Booking save(Booking booking) {
		return bookingRepo.save(booking);
	}
	
	
	
}
