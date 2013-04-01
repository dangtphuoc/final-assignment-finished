package springdata.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springdata.jpa.model.Location;
import springdata.jpa.repository.LocationRepository;

import com.google.common.collect.Lists;

@Service
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

	public Location updateLocation(Location location) {
		Location oldLocation = locationRepository.findOne(location.getId());
		oldLocation.setTitle(location.getTitle());
		oldLocation.setDescription(location.getDescription());
		return oldLocation;
	}
}
