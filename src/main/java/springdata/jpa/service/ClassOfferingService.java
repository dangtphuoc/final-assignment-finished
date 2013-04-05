package springdata.jpa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springdata.jpa.model.ClassOffering;
import springdata.jpa.repository.ClassOfferingRepository;

@Service
@Transactional(readOnly=true)
public class ClassOfferingService {
	
	@Autowired
	private ClassOfferingRepository classOfferingRepository;

	public List<ClassOffering> searchClassOfferings(String key, Date startDate,
			Date endDate) {
		return classOfferingRepository.searchClassOfferings(key, startDate, endDate);
	}

}
