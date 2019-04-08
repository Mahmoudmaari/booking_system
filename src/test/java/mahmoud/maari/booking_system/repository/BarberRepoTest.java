package mahmoud.maari.booking_system.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.Client;
import mahmoud.maari.booking_system.models.HaircutStyle;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class BarberRepoTest {
	@Autowired private BarberRepo barberTest;
	@Autowired private ClientRepo clientTest;
	@Autowired private HaircutStyleRepo haircutTest;
	@Autowired private BookingRepo bookingTest;
	 
	private Barber barber1;
	private Client client1;
	private HaircutStyle haircut1;
	private Booking booking1;
	private int idBarber;
	private int idClient;
	private int idHaircut;
	private int idbooking;
	@Before
	public void init() {
		List<Client> clients = new ArrayList<>();
		Client client = new Client("TestClient", LocalDate.parse("1995-09-14"), "male", "079-1234567", "test@test.com", "1234567890");
		clients.add(client);
		Barber barber = new Barber("TestBarber", "079-0000000");
		barber.addClient(client);
		HaircutStyle haircut= new HaircutStyle("Test", "test : test", 300);
		Booking booking = new Booking(LocalDate.parse("2019-04-04"),LocalTime.parse("15:00:00"));
		this.haircut1 = haircutTest.save(haircut);
		this.barber1=barberTest.save(barber);
		this.client1=clientTest.save(client);
		this.booking1=bookingTest.save(booking);
		this.idBarber=barber.getId();
		this.idClient=client.getId();
		this.idHaircut = haircut.getId();
		this.idbooking = booking.getId();
		
	}
	
	@Test
	public void barberIsPresent_test() {
		Optional<Barber> result= barberTest.findById(idBarber);
		assertTrue(result.isPresent());
	    assertEquals(result.get(), barber1);
	}
	@Test
	public void clientIsPresent_test() {
		Optional<Client> expected = clientTest.findById(idClient);
		assertTrue(expected.isPresent());
		assertEquals(expected.get(), client1);
	}
	@Test
	public void haircutIsPresent_test() {
		Optional<HaircutStyle> expected = haircutTest.findById(idHaircut);
		assertTrue(expected.isPresent());
		assertEquals(expected.get(), haircut1);
	}
	@Test
	public void bookingIsPresent_test() {
		Optional<Booking> expected = bookingTest.findById(idbooking);
		assertTrue(expected.isPresent());
		assertEquals(expected.get(), booking1);
	}
	@Test
	public void findBarberByName_test() {
		String name = "testbarber";
		List<Barber> expected = barberTest.findByNameIgnoreCase(name);
		List<Barber> actual = Arrays.asList(barber1);
		assertEquals(expected, actual);
	}
	@Test
	public void findClientByName_test() {
		String name = "testClient";
		List<Client> expected = clientTest.findByclientNameIgnoreCase(name);
		List<Client> actual = Arrays.asList(client1);
		assertEquals(expected, actual);
	}
	@Test
	public void findHaircutByName_test() {
		String name = "test";
		List<HaircutStyle> expected = haircutTest.findByhaircutTypeIgnoreCase(name);
		List<HaircutStyle> actual = Arrays.asList(haircut1);
		assertEquals(expected, actual);
	}
	@Test
	public void findBookingByDate() {
		LocalDate date = LocalDate.parse("2019-04-04");
		List<Booking> expected = bookingTest.findBybookingDate(date);
		List<Booking> actual = Arrays.asList(booking1);
		assertEquals(expected, actual);
	}
	@Test
	public void findBookingByTime() {
		LocalTime time= LocalTime.parse("15:00:00");
		List<Booking> expected = bookingTest.findBybookingTime(time);
		List<Booking> actual = Arrays.asList(booking1) ;
		assertEquals(expected, actual);
	}
}
