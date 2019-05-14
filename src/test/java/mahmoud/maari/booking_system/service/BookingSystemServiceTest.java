package mahmoud.maari.booking_system.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class BookingSystemServiceTest {

	@Autowired
	private BarberRepo barberRepo;
	@Autowired
	private ClientRepo clientRepo;
	@Autowired
	private HaircutStyleRepo haircutRepo;
	@Autowired
	private BookingRepo bookingRepo;
	@Autowired
	private BarberRateRepo RateRepo;

	private Barber barber1;
	private ClientC client1;
	private HaircutStyle haircut1;
	private Booking booking1;
	private BarberRate Rate1;
	private BarberService barberSV;
	private BookingService bookingSV;
	private ClientService clientSV;
	private BarberRateService rateSV;
	private HaircutStyleService haircutSV;

	@Before
	public void init() {
		List<ClientC> clients = new ArrayList<>();
		ClientC client = new ClientC("TestClient", LocalDate.parse("1995-09-14"), "male", "079-1234567",
				"test@test.com", "1234567890");
		clients.add(client);
		Barber barber = new Barber("TestBarber", "079-0000000", true);

		HaircutStyle haircut = new HaircutStyle("Test", "test : test", 300);
		BarberRate rate = new BarberRate(new BigDecimal(4.7));

		Booking booking = new Booking(LocalDate.parse("2019-04-04"), LocalTime.parse("15:00:00"), true);

		this.haircut1 = haircutRepo.save(haircut);
		this.barber1 = barberRepo.save(barber);
		this.client1 = clientRepo.save(client);
		this.booking1 = bookingRepo.save(booking);
		this.Rate1 = RateRepo.save(rate);
		this.barberSV = new BarberServiceImpl(barberRepo);
		this.bookingSV = new BookingServiceImpl(bookingRepo);
		this.rateSV = new BarberRateServiceImpl(RateRepo);
		this.clientSV = new ClientServiceImpl(clientRepo);
		this.haircutSV = new HaircutStyleServiceImpl(haircutRepo);

	}

	@Test
	public void addBarberToClientTest() {
		assertTrue(barberSV.addBookingToBarber(booking1, barber1));
	}

	@Test
	public void addBookingToClientTest() {
		assertTrue(clientSV.addBookingToClient(booking1, client1));
	}

	@Test
	public void addHaircutToClientTest() {
		assertTrue(haircutSV.addBookingToHaircut(haircut1, booking1));
	}

	@Test
	public void findBarberInBooking() {
		barberSV.addBookingToBarber(booking1, barber1);
		List<Booking> booking = new ArrayList<>();
		booking.addAll(bookingSV.findBookingByBarberId(barber1));
		List<Booking> actual = new ArrayList<>();
		actual.add(booking1);
		assertEquals(booking, actual);

	}

	@Test
	public void rateCalTest() {
		Booking booking2 = new Booking(LocalDate.parse("2019-04-04"), LocalTime.parse("15:00:00"), true);
		BarberRate rate = new BarberRate(new BigDecimal(4));
		bookingSV.save(booking2);
		rateSV.save(rate);
		barberSV.addBookingToBarber(booking2, barber1);
		rateSV.addRateToBooking(rate, booking2);
		rateSV.addRateToBooking(Rate1, booking1);

		barberSV.addBookingToBarber(booking1, barber1);
		BigDecimal actual = bookingSV.RateCal(barber1);
		BigDecimal expected = new BigDecimal(4.35).setScale(1, RoundingMode.DOWN);
		assertEquals(expected, actual);
	}
 
	@Test
	public void RemoveClientFromBooking() {
		clientSV.addBookingToClient(booking1, client1);
		assertTrue(clientSV.removeClientFromBooking(booking1, client1));
	}
	@Test
	public void RemoveHaieCutFromBooking() {
		haircutSV.addBookingToHaircut(haircut1, booking1);
		assertTrue(haircutSV.RemoveHiarcutFromBooking(haircut1, booking1));
	}
	@Test
	public void RemoveBarberFromBooking() {
		barberSV.addBookingToBarber(booking1, barber1);
		assertTrue(barberSV.removeBarberFromBooking(booking1, barber1));
	}
}
