package springdata.jpa.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springdata.jpa.model.Curriculum;
import springdata.jpa.querydsl.predicate.CurriculaPredicate;
import springdata.jpa.repository.CurriculumRepository;

import com.google.common.collect.Lists;
import com.mysema.query.types.Predicate;

@Service
@Transactional(readOnly=true)
public class CurriculumService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurriculumService.class);
	
	@Autowired
	private CurriculumRepository curriculumRepository;
	
	@Transactional
	public Curriculum createUpdateCurriculum(Curriculum curriculum) {
		if(curriculum.getId() == null) {
			LOGGER.debug("creating curriculum" + curriculum.getTitle());
			
			curriculum = curriculumRepository.save(curriculum);
		} else {
			LOGGER.debug("updating curriculum " + curriculum.getTitle());
			
			Curriculum managedCurriculum = curriculumRepository.findOne(curriculum.getId());
			if(managedCurriculum != null) {
				curriculum = managedCurriculum.updateCurriculum(curriculum);
			}
		}
		return curriculum;
	}

	public List<Curriculum> getCurricula() {
		return Lists.newArrayList(curriculumRepository.findAll());
	}

	public Curriculum getCurriculum(Long id) {
		return curriculumRepository.getCurriculumById(id);
	}

	@Transactional
	public void deleteCurriculum(Long id) {
		Curriculum curriculum = curriculumRepository.findOne(id);
		if(curriculum != null) {
			 curriculumRepository.delete(curriculum);
		}
	}

	public List<Curriculum> searchCurricula(String key) {
		return curriculumRepository.searchCurricula(key);
	}
}
