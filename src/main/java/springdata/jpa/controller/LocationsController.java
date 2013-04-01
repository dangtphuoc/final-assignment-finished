package springdata.jpa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springdata.jpa.common.ErrorType;
import springdata.jpa.dto.ResponseBean;
import springdata.jpa.model.Location;
import springdata.jpa.service.LocationService;



@RequestMapping("/locations")
@Controller
public class LocationsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationsController.class);
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<Location> getLocations() {
		return locationService.getLocations();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseBean getLocations(@RequestBody Location location) {
		location = locationService.createLocation(location);
		ErrorType error = ErrorType.SUCCESS;
		if(location.getId() != null) {
    		error = ErrorType.FAIL;
    	}
        return new ResponseBean(error);
	}
}
