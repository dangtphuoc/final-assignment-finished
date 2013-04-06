package springdata.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springdata.jpa.model.Location;
import springdata.jpa.repository.LocationRepository;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;
	
	public Location createLocation(Location location) {
		return locationRepository.save(location);
	}

	public List<Location> getLocations() {
		return Lists.newArrayList(locationRepository.findAll());
	}

	public Location getLocation(Long locationId) {
		return locationRepository.findOne(locationId);
	}

	@Transactional(readOnly = false)
	public Location updateLocation(Location location) {
		Location managedLocation = locationRepository.findOne(location.getId());
		if(managedLocation != null) {
			managedLocation.setTitle(location.getTitle());
			managedLocation.setDescription(location.getDescription());
			managedLocation.setContactPerson(location.getContactPerson());
			managedLocation.setTelephone(location.getTelephone());
		}
		return managedLocation;
	}
}
