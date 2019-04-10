package mahmoud.maari.booking_system.service;

import java.util.List;

import mahmoud.maari.booking_system.models.BarberRate;

public interface BarberRateService {

	BarberRate findById(int id);

	List<BarberRate> findAll();

	boolean removeRate(int id);

	BarberRate save(BarberRate barberRate);

}