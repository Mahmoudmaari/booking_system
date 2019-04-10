package mahmoud.maari.booking_system.service;

import java.util.List;

import mahmoud.maari.booking_system.models.Client;
import mahmoud.maari.booking_system.models.HaircutStyle;

public interface HaircutStyleService {

	HaircutStyle findById(int id);

	List<HaircutStyle> findAll();

	List<HaircutStyle> findByName(String name);

	boolean removeHaircut(int id);

	HaircutStyle save(HaircutStyle haircutStyle);

	boolean addHaircutTOclient(HaircutStyle h, Client c);

}