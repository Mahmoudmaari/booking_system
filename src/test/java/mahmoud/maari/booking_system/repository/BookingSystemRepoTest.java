package mahmoud.maari.booking_system.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class BookingSystemRepoTest {
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
	private int barberId;
	private int clientId;
	private int haircutId;
	private int bookingId;
	private int RateId;
	

	@Before
	public void init() {
		List<ClientC> clients = new ArrayList<>();
		ClientC client = new ClientC("TestClient", LocalDate.parse("1995-09-14"), "male", "079-1234567", "test@test.com",
				"1234567890");
		clients.add(client);
		Barber barber = new Barber("TestBarber", "079-0000000",true);

		HaircutStyle haircut = new HaircutStyle("Test", "test : test", 300,01,30);
		BarberRate rate = new BarberRate(new BigDecimal(4.7));
		
		Booking booking = new Booking(LocalDate.parse("2019-04-04"), LocalTime.parse("15:00:00"),true);
		
	
		this.haircut1 = haircutRepo.save(haircut);
		this.barber1 = barberRepo.save(barber);
		this.client1 = clientRepo.save(client);
		this.booking1 = bookingRepo.save(booking);
		this.Rate1 = RateRepo.save(rate);
		this.barberId = barber.getId();
		this.clientId = client.getId();
		this.haircutId = haircut.getId();
		this.bookingId = booking.getId();
		this.RateId = rate.getId();
		

	}

	@Test
	public void barberIsPresent_test() {
		Optional<Barber> expected = barberRepo.findById(barberId);
		assertTrue(expected.isPresent());
		assertEquals(expected.get(), barber1);
	}

	@Test
	public void clientIsPresent_test() {
		Optional<ClientC> expected = clientRepo.findById(clientId);
		assertTrue(expected.isPresent());
		assertEquals(expected.get(), client1);
	}

	@Test
	public void haircutIsPresent_test() {
		Optional<HaircutStyle> expected = haircutRepo.findById(haircutId);
		assertTrue(expected.isPresent());
		assertEquals(expected.get(), haircut1);
	}

	@Test
	public void bookingIsPresent_test() {
		Optional<Booking> expected = bookingRepo.findById(bookingId);
		assertTrue(expected.isPresent());
		assertEquals(expected.get(), booking1);
	}

	@Test
	public void barberRateIsPresent_test() {
		Optional<BarberRate> expected = RateRepo.findById(RateId);
		assertTrue(expected.isPresent());
		assertEquals(expected.get(), Rate1);
	}

	@Test
	public void findBarberByName_test() {
		String name = "testbarber";
		List<Barber> expected = barberRepo.findByNameIgnoreCase(name);
		List<Barber> actual = Arrays.asList(barber1);
		assertEquals(expected, actual);
	}

	@Test
	public void findClientByName_test() {
		String name = "testClient";
		List<ClientC> expected = clientRepo.findByclientNameIgnoreCase(name);
		List<ClientC> actual = Arrays.asList(client1);
		assertEquals(expected, actual);
	}

	@Test
	public void findHaircutByName_test() {
		String name = "test";
		List<HaircutStyle> expected = haircutRepo.findByHaircutTypeIgnoreCase(name);
		List<HaircutStyle> actual = Arrays.asList(haircut1);
		assertEquals(expected, actual);
	}

	@Test
	public void findBookingByDate() {
		LocalDate date = LocalDate.parse("2019-04-04");
		List<Booking> expected = bookingRepo.findBybookingDate(date);
		List<Booking> actual = Arrays.asList(booking1);
		assertEquals(expected, actual);
	}

	@Test
	public void findBookingByTime() {
		LocalTime time = LocalTime.parse("15:00:00");
		List<Booking> expected = bookingRepo.findBybookingTime(time);
		List<Booking> actual = Arrays.asList(booking1);
		assertEquals(expected, actual);
	}

	
	
	
}
