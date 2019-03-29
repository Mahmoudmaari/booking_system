package mahmoud.maari.booking_system;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.Client;
import mahmoud.maari.booking_system.models.HaircutStyle;
import mahmoud.maari.booking_system.repository.BarberRepo;
import mahmoud.maari.booking_system.repository.BookingRepo;
import mahmoud.maari.booking_system.repository.ClientRepo;
import mahmoud.maari.booking_system.repository.HaircutStyleRepo;

@Component
@Transactional(rollbackFor = Exception.class)
public class TestCommandLine implements CommandLineRunner {
	
	private BarberRepo Brepo;
	private ClientRepo Crepo;
	private BookingRepo Krepo;
	private HaircutStyleRepo Hrepo;
	

	public TestCommandLine(BarberRepo brepo, ClientRepo crepo, BookingRepo krepo, HaircutStyleRepo hrepo) {
		super();
		this.Brepo = brepo;
		this.Crepo = crepo;
		this.Krepo = krepo;
		this.Hrepo = hrepo;
	}


	@Override
	public void run(String... args) throws Exception {
		
				
				Barber barber = Brepo.save(new Barber("test", 4.5, "079-090909090"));
				Barber barber1 = Brepo.save(new Barber("test1", 4.9, "079-90904543"));
				

			   Client client = new Client("kakaka",LocalDate.parse("1995-09-14"), "male", "079-123456", "mahmoud@gmail.com");
			    Crepo.save(client);
			    Client client1=Crepo.save(new Client("test", LocalDate.parse("1991-04-21"), "test", "079-019921212", "test@test.com"));
			    Crepo.save(client1);
			    Booking booking = new Booking(LocalDate.now(), LocalTime.now());
			    Krepo.save(booking);
			    HaircutStyle cut = new HaircutStyle("normal", "normal hair cut", 250);
			    Hrepo.save(cut);

			  client.setBooking(booking);
			  
			   barber.addClient(client);
			   barber1.addClient(client1);
		
		  System.out.println(barber+"\n"+barber1);
	}

	
}
