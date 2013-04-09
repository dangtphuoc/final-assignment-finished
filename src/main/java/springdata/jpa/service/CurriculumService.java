package springdata.jpa.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import springdata.jpa.dto.CurriculumDTO;
import springdata.jpa.model.Curriculum;
import springdata.jpa.model.CurriculumRegistration;
import springdata.jpa.model.Student;
import springdata.jpa.repository.CurriculumRepository;
import springdata.jpa.repository.StudentRepository;

import com.google.common.collect.Lists;

@Service
@Transactional(readOnly=true)
public class CurriculumService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurriculumService.class);
	
	@Autowired
	private CurriculumRepository curriculumRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional(readOnly=false)
	public Curriculum createCurriculum(CurriculumDTO curriculumDTO) {
		Curriculum curriculum = curriculumDTO.toCurriculum();
		
		LOGGER.debug("creating curriculum" + curriculum.getTitle());
		
		curriculum = curriculumRepository.save(curriculum);
		return curriculum;
	}
	
	@Transactional(readOnly=false)
	public Curriculum updateCurriculum(CurriculumDTO curriculumDTO) {
		Curriculum curriculum = curriculumDTO.toCurriculum();
		
		LOGGER.debug("updating curriculum " + curriculum.getTitle());
		
		Curriculum managedCurriculum = curriculumRepository.findOne(curriculum.getId());
		if(managedCurriculum != null) {
			curriculum = managedCurriculum.updateCurriculum(curriculum);
		}
		return curriculum;
	}

	public List<Curriculum> getCurricula(String key) {
		return Lists.newArrayList(curriculumRepository.searchCurricula(key));
	}

	public Curriculum getCurriculum(Long id) {
		return curriculumRepository.getCurriculumById(id);
	}

	@Transactional(readOnly=false)
	public void deleteCurriculum(Long id) {
		Curriculum curriculum = curriculumRepository.findOne(id);
		if(curriculum != null) {
			 curriculumRepository.delete(curriculum);
		}
	}

	@Transactional(readOnly=false)
	public void enrollCurricula(List<Long> curriculumIds, List<Long> studentIds, BindingResult result) {
		for(Long curriculumId : curriculumIds) {
			Curriculum curriculum = curriculumRepository.findOne(curriculumId);
			for(Long studentId : studentIds) {
				Student student = studentRepository.findOne(studentId);
				if(!curriculum.isStudentAlreadyEnrolled(student)) {
					CurriculumRegistration curriculumRegistration = new CurriculumRegistration();
					curriculumRegistration.setCurriculum(curriculum);
					curriculumRegistration.setStudent(student);
					curriculumRegistration.setEnrolledDate(new Date());
					curriculum.getEnrolledStudents().add(curriculumRegistration);
				}
			}
		}
	}

	public List<Curriculum> searchCurricula(String key, String withCourse) {
		List<Curriculum> curricula = curriculumRepository.searchCurricula(key, withCourse);
		return curricula;
	}
}
