package mahmoud.maari.booking_system;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.BarberRate;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.Client;
import mahmoud.maari.booking_system.models.HaircutStyle;
import mahmoud.maari.booking_system.repository.BarberRateRepo;
import mahmoud.maari.booking_system.repository.BarberRepo;
import mahmoud.maari.booking_system.repository.BookingRepo;
import mahmoud.maari.booking_system.repository.ClientRepo;
import mahmoud.maari.booking_system.repository.HaircutStyleRepo;

@Component
@Transactional(rollbackFor = Exception.class)
public class CommandLine implements CommandLineRunner {

	private BarberRepo barberRepo;
	private ClientRepo clientRepo;
	private BookingRepo bookingRepo;
	private HaircutStyleRepo haircutRepo;
	private BarberRateRepo rateRepo;

	public CommandLine(BarberRepo barberRepo, ClientRepo clientRepo, BookingRepo bookingRepo,
			HaircutStyleRepo haircutRepo, BarberRateRepo rateRepo) {
		super();
		this.barberRepo = barberRepo;
		this.clientRepo = clientRepo;
		this.bookingRepo = bookingRepo;
		this.haircutRepo = haircutRepo;
		this.rateRepo = rateRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		Barber barber = new Barber("Barber1", "079-xxxxxxx");
		Barber barber2 = new Barber("Barber2", "079-xxxxxxx");
		Client client1 = new Client("Client1", LocalDate.parse("1995-09-14"), "male", "079-xxxxxxx",
				"client1@clientmail.com", "1234");
		Client client2 = new Client("Client2", LocalDate.parse("2000-05-20"), "female", "079-xxxxxxx",
				"Client1@gmail.com", "1234");
		Booking booking1 = new Booking(LocalDate.now(), LocalTime.now());
		Booking booking2 = new Booking(LocalDate.now(), LocalTime.now());
		HaircutStyle haircut1 = new HaircutStyle("Normal", "Normal haircut", 250);
		HaircutStyle haircut2 = new HaircutStyle("Zero", "Zero haircit", 200);
		List<BigDecimal> r = new ArrayList<>();
		r.add(new BigDecimal(4.5));
		r.add(new BigDecimal(4.9));
		r.add(new BigDecimal(4.0));
		r.add(new BigDecimal(3.5));
		r.add(new BigDecimal(5.0));

		BarberRate rate = new BarberRate(r, new BigDecimal(0));
		rate.RateCal(r);
		barber.addClient(client1);
		barber.addClient(client2);
		booking1.addClient(client1);
		booking1.addClient(client2);
		haircut1.addClient(client1);
		haircut1.addClient(client2);
		barberRepo.save(barber);
		barberRepo.save(barber2);
		clientRepo.save(client1);
		clientRepo.save(client2);
		bookingRepo.save(booking1);
		bookingRepo.save(booking2);
		haircutRepo.save(haircut1);
		haircutRepo.save(haircut2);
		rateRepo.save(rate);
		System.out.println(rate.getOldRate()+"  "+rate.getRateResult());

	}

}