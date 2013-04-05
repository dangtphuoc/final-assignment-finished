package springdata.jpa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springdata.jpa.common.ErrorType;
import springdata.jpa.dto.ClassOfferingDTO;
import springdata.jpa.dto.CourseDTO;
import springdata.jpa.dto.ResponseBean;
import springdata.jpa.model.ClassOffering;
import springdata.jpa.model.Course;
import springdata.jpa.service.ClassOfferingService;
import springdata.jpa.service.CourseService;



@RequestMapping("/classofferings")
@Controller
public class ClassOfferingsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassOfferingsController.class);
	private static final String COURSE_HOME_VIEW = "courses";
	
	@Autowired
	private ClassOfferingService classOfferingService;
	
	@RequestMapping(value="/search", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<ClassOfferingDTO> searchClassOfferings(@RequestParam(value="key", required=false) String key, 
			@RequestParam(value="startDate", required=false) Date startDate,
			@RequestParam(value="endDate", required=false) Date endDate) {
		List<ClassOffering> classOfferings = classOfferingService.searchClassOfferings(key, startDate, endDate);
		
		//build json class offering
		List<ClassOfferingDTO> classOfferingDTOs = new ArrayList<ClassOfferingDTO>();
		if(classOfferings != null) {
			for(ClassOffering classOffering : classOfferings) {
				ClassOfferingDTO classOfferingDTO = new ClassOfferingDTO(classOffering);
				classOfferingDTOs.add(classOfferingDTO);
			}
		}
		return classOfferingDTOs;
	}
	
}
