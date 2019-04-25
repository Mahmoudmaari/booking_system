package mahmoud.maari.booking_system.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mahmoud.maari.booking_system.form.BarberRateForm;
import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.BarberRate;
import mahmoud.maari.booking_system.models.ClientC;
import mahmoud.maari.booking_system.service.BarberRateService;
import mahmoud.maari.booking_system.service.BarberService;
import mahmoud.maari.booking_system.service.ClientService;

@RestController
public class BarberRateController {

	private BarberRateService rateSV;
	private BarberService barberSV;
	private ClientService clientSV;
	
	@Autowired
	public BarberRateController(BarberRateService rateSV, BarberService barberSV, ClientService clientSV) {
		super();
		this.rateSV = rateSV;
		this.barberSV = barberSV;
		this.clientSV = clientSV;
	}
	
	@GetMapping("/BarberRate")
	public ResponseEntity<List<BarberRate>> getAllRate(){
		List<BarberRate> rate = rateSV.findAll();
		if(rate.isEmpty()) {
		return	ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(rate);
				
	}
	@PostMapping("/BarberRate")
	public ResponseEntity<BarberRate> create (@Valid @RequestBody BarberRateForm rateForm){
		BarberRate rate = rateSV.save(new BarberRate(rateForm.getStarRate()));
		rate.setRateResult(rate.RateCal(rate.getStarRate()));
		return ResponseEntity.status(HttpStatus.CREATED).body(rate);
	}
	
	@PostMapping("/BarberRate/{id}")
	public ResponseEntity<BarberRate> add(@PathVariable int id,@Valid @RequestBody BarberRateForm rateForm){
		
		List<BigDecimal> newRate = rateForm.getStarRate();
		BarberRate rate = rateSV.findById(id);
		rate.getStarRate().addAll(newRate);
		rate.setRateResult(rate.RateCal(rate.getStarRate()));
		return ResponseEntity.ok().body(rateSV.save(rate));
	}
	
	@GetMapping("/BarberRate/{id}")
	public ResponseEntity<BarberRate> findById(@PathVariable int id){
		return ResponseEntity.ok().body(rateSV.findById(id));		
	}
	
	@PostMapping("BarberRate/Barber/{BID}/{RID}")
	public ResponseEntity<Boolean> addRateToBarber(@PathVariable int BID ,@PathVariable int RID){
		if(rateSV.findById(RID)==null || barberSV.findById(BID)==null) {
			return ResponseEntity.notFound().build();
		}
		BarberRate rate = rateSV.findById(RID);
		Barber barber = barberSV.findById(BID);
		return ResponseEntity.ok(barberSV.addRateToBarber(barber, rate));
	}
	@PostMapping("BarberRate/Client/{CID}/{RID}")
	public ResponseEntity<Boolean> addRateToClient(@PathVariable int CID ,@PathVariable int RID){
		if(rateSV.findById(RID)==null || clientSV.findById(CID)==null) {
			return ResponseEntity.notFound().build();
		}
		List<ClientC> c = new ArrayList<>();
		BarberRate rate = rateSV.findById(RID);
		ClientC client = clientSV.findById(CID);
		
		return ResponseEntity.ok(clientSV.takeRateFromClient(client, rate));
	}
	
}
