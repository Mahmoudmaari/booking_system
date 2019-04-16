package mahmoud.maari.booking_system;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
import mahmoud.maari.booking_system.service.BarberRateService;
import mahmoud.maari.booking_system.service.BarberRateServiceImpl;
import mahmoud.maari.booking_system.service.BarberServiceImpl;
import mahmoud.maari.booking_system.service.BookingService;
import mahmoud.maari.booking_system.service.BookingServiceImpl;
import mahmoud.maari.booking_system.service.ClientService;
import mahmoud.maari.booking_system.service.ClientServiceImpl;
import mahmoud.maari.booking_system.service.HaircutStyleService;
import mahmoud.maari.booking_system.service.HaircutStyleServiceImpl;
import mahmoud.maari.booking_system.service.BarberService;

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
		Client client1 = new Client("Client1", LocalDate.parse("1995-09-14"), "male", "079-xxxxxxx",
				"client1@clientmail.com", "1234");
		Client client2 = new Client("Client2", LocalDate.parse("2000-05-20"), "female", "079-xxxxxxx",
				"Client1@gmail.com", "1234");
		Booking booking1 = new Booking(LocalDate.now(), LocalTime.parse("15:14"),true);
		Booking booking2 = new Booking(LocalDate.now(), LocalTime.now(),true);
		HaircutStyle haircut1 = new HaircutStyle("Normal", "Normal haircut", 250);
		HaircutStyle haircut2 = new HaircutStyle("Zero", "Zero haircit", 200);
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
		BarberRate rate = new BarberRate(r1, new BigDecimal(0));
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
		BarberRate rate1 = new BarberRate(r, new BigDecimal(0));
		barberRepo.save(barber);
		barberRepo.save(barber2);
		clientRepo.save(client1);
		clientRepo.save(client2);
		bookingRepo.save(booking1);
		bookingRepo.save(booking2);
		haircutRepo.save(haircut1);
		haircutRepo.save(haircut2);
		rateRepo.save(rate);
		rateRepo.save(rate1);
		barberSV.addBarberToClient(client1,barber);
		barberSV.addBarberToClient(client2,barber2);
		bookingSV.addBookingToClient(booking1, client1);
		bookingSV.addBookingToClient(booking2, client2);
		haircutSV.addHaircutTOclient(haircut1, client1);
		haircutSV.addHaircutTOclient(haircut1, client2);
		rate.RateCal(r1);
		rate1.RateCal(r);
		System.out.println(rate.getOldRate()+"  "+rate.getRateResult());
		clientSV.takeRateFromClient(client1, rate);
		clientSV.takeRateFromClient(client2, rate1);
		barberSV.addRateToBarber(barber, rate);
		barberSV.addRateToBarber(barber2, rate1);
		barberSV.findAll().forEach(System.out::println);
		clientSV.findAll().forEach(System.out::println);
		bookingSV.findAll().forEach(System.out::println);
		haircutSV.findAll().forEach(System.out::println);
		rateSV.findAll().forEach(System.out::println);
		Booking booking3= new Booking(LocalDate.now(), LocalTime.now(),true);
		bookingSV.save(booking3);
		clientSV.removeClinet(1);
		bookingSV.removec(booking1, client1);
		System.out.println(bookingSV.removeBooking(1));
		clientSV.findAll().forEach(System.out::println);
	}

}
