package springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import springdata.jpa.model.Curriculum;
import springdata.jpa.model.QCourse;
import springdata.jpa.model.QCurriculum;

public class CurriculumRepositoryImpl extends QueryDslRepositorySupport implements CurriculumRepositoryCustom {

	public CurriculumRepositoryImpl() {
		super(Curriculum.class);
	}
	
	@Override
	public List<Curriculum> searchCurricula(String key) {
		QCurriculum curriculum = QCurriculum.curriculum;
		QCourse course = QCourse.course;
		return from(curriculum).innerJoin(curriculum.courses, course)
		.where(curriculum.title.containsIgnoreCase(key)
				.or(curriculum.description.containsIgnoreCase(key)
						.or(course.title.containsIgnoreCase(key)))).distinct().list(curriculum);
	}

}