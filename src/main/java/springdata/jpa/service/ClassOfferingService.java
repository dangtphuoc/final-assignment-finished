package springdata.jpa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import springdata.jpa.model.ClassOffering;
import springdata.jpa.model.ClassOfferingRegistration;
import springdata.jpa.model.Student;
import springdata.jpa.repository.ClassOfferingRepository;
import springdata.jpa.repository.StudentRepository;

@Service
@Transactional(readOnly=true)
public class ClassOfferingService {
	
	@Autowired
	private ClassOfferingRepository classOfferingRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	public List<ClassOffering> searchClassOfferings(String key, Date startDate, Date endDate) {
		return classOfferingRepository.searchClassOfferings(key, startDate, endDate);
	}

	@Transactional(readOnly=false)
	public void enrollClassOfferings(List<Long> classOfferingIds, List<Long> studentIds, BindingResult result) {
		for(Long classOfferingId : classOfferingIds) {
			ClassOffering classOffering = classOfferingRepository.findOne(classOfferingId);
			for(Long studentId : studentIds) {
				Student student = studentRepository.findOne(studentId);
				if(!classOffering.isStudentAlreadyEnrolled(student)) {
					ClassOfferingRegistration classOfferingRegistration = new ClassOfferingRegistration();
					classOfferingRegistration.setClassOffering(classOffering);
					classOfferingRegistration.setStudent(student);
					classOfferingRegistration.setEnrolledDate(new Date());
					classOffering.getEnrolledStudents().add(classOfferingRegistration);
				}
			}
		}
	}

}
