package mahmoud.maari.booking_system;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.BarberRate;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.ClientC;
import mahmoud.maari.booking_system.models.HaircutStyle;
import mahmoud.maari.booking_system.repository.BarberRateRepo;
import mahmoud.maari.booking_system.repository.BarberRepo;
import mahmoud.maari.booking_system.repository.BookingRepo;
import mahmoud.maari.booking_system.repository.ClientRepo;
import mahmoud.maari.booking_system.repository.HaircutStyleRepo;
import mahmoud.maari.booking_system.service.BarberRateService;
import mahmoud.maari.booking_system.service.BarberRateServiceImpl;
import mahmoud.maari.booking_system.service.BarberService;
import mahmoud.maari.booking_system.service.BarberServiceImpl;
import mahmoud.maari.booking_system.service.BookingService;
import mahmoud.maari.booking_system.service.BookingServiceImpl;
import mahmoud.maari.booking_system.service.ClientService;
import mahmoud.maari.booking_system.service.ClientServiceImpl;
import mahmoud.maari.booking_system.service.HaircutStyleService;
import mahmoud.maari.booking_system.service.HaircutStyleServiceImpl;

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
		BarberService barberSV = new BarberServiceImpl(barberRepo);
		BookingService bookingSV = new BookingServiceImpl(bookingRepo);
		HaircutStyleService haircutSV= new HaircutStyleServiceImpl(haircutRepo);
		ClientService clientSV= new ClientServiceImpl(clientRepo);
		BarberRateService rateSV = new BarberRateServiceImpl(rateRepo);
		Barber barber = new Barber("Barber1", "079-xxxxxxx",true);
		Barber barber2 = new Barber("Barber2", "079-xxxxxxx",true);
		ClientC client1 = new ClientC("Client1", LocalDate.parse("1995-09-14"), "male", "079-xxxxxxx",
				"client1@clientmail.com", "1234");
		ClientC client2 = new ClientC("Client2", LocalDate.parse("2000-05-20"), "female", "079-xxxxxxx",
				"Client1@gmail.com", "1234");
		ClientC client3 = new ClientC("Client2", LocalDate.parse("2000-05-20"), "female", "079-xxxxxxx",
				"Client1@gmail.com", "1234");
		ClientC client4= new ClientC("Client2", LocalDate.parse("2000-05-20"), "female", "079-xxxxxxx",
				"Client1@gmail.com", "1234");
		Booking booking1 = new Booking(LocalDate.now(), LocalTime.parse("15:14"),true);
		Booking booking2 = new Booking(LocalDate.now(), LocalTime.now(),true);
		
		HaircutStyle haircut1 = new HaircutStyle("Normal", "Normal haircut", 250);
		HaircutStyle haircut2 = new HaircutStyle("Zero", "Zero haircit", 200);
		List<ClientC> clients1 = Arrays.asList(client1);
		List<ClientC> clients2 = new ArrayList<>();
		clients2.add(client2);
		clients2.add(client3);
		List<BigDecimal> r1 = new ArrayList<>();
		r1.add(new BigDecimal(4.5));
		r1.add(new BigDecimal(4.0));
		r1.add(new BigDecimal(3.0));
		r1.add(new BigDecimal(5.0));
		r1.add(new BigDecimal(5.0));
		r1.add(new BigDecimal(4.5));
		r1.add(new BigDecimal(4.5));
		r1.add(new BigDecimal(4.5));
		r1.add(new BigDecimal(4.5));
		r1.add(new BigDecimal(4.5));
		r1.add(new BigDecimal(4.5));
		r1.add(new BigDecimal(4.0));
		r1.add(new BigDecimal(4.0));
		r1.add(new BigDecimal(3.0));
		r1.add(new BigDecimal(3.0));
		r1.add(new BigDecimal(3.0));
		r1.add(new BigDecimal(3.0));
		r1.add(new BigDecimal(3.0));
		BarberRate rate = new BarberRate(r1);
		List<BigDecimal> r = new ArrayList<>();
		r.add(new BigDecimal(4.5));
		r.add(new BigDecimal(4.0));
		r.add(new BigDecimal(3.0));
		r.add(new BigDecimal(5.0));
		r.add(new BigDecimal(5.0));
		r.add(new BigDecimal(4.5));
		r.add(new BigDecimal(4.5));
		r.add(new BigDecimal(4.5));
		r.add(new BigDecimal(4.5));
		r.add(new BigDecimal(4.5));
		r.add(new BigDecimal(4.5));
		r.add(new BigDecimal(4.0));
		r.add(new BigDecimal(4.0));
		r.add(new BigDecimal(3.0));
		r.add(new BigDecimal(3.0));
		r.add(new BigDecimal(3.0));
		r.add(new BigDecimal(3.0));
		r.add(new BigDecimal(3.0));
		BarberRate rate1 = new BarberRate(r);
		barberRepo.save(barber);
		barberRepo.save(barber2);
		clientRepo.save(client1);
		clientRepo.save(client2);
		clientRepo.save(client3);
		clientRepo.save(client4);
		bookingRepo.save(booking1);
		bookingRepo.save(booking2);
		haircutRepo.save(haircut1);
		haircutRepo.save(haircut2);
		rateRepo.save(rate);
		rateRepo.save(rate1);
		barberSV.addBookingToBarber(booking1,barber);
		barberSV.addBookingToBarber(booking2,barber2);
		clientSV.addBookingToClient(booking1, client1);
		clientSV.addBookingToClient(booking2, client2);
		haircutSV.addBookingToHaircut(haircut1, booking1);
		haircutSV.addBookingToHaircut(haircut1, booking2);
		rate.RateCal(r1);
		rate1.RateCal(r);
		System.out.println(rate.getOldRate()+"  "+rate.getRateResult());
		barberSV.findAll().forEach(System.out::println);
		clientSV.findAll().forEach(System.out::println);
		bookingSV.findAll().forEach(System.out::println);
		haircutSV.findAll().forEach(System.out::println);
		
		Booking booking3= new Booking(LocalDate.now(), LocalTime.now(),true);
		bookingSV.save(booking3);
		clientSV.addBookingToClient(booking3, client1);
		clientSV.findAll().forEach(System.out::println);
	}

}
