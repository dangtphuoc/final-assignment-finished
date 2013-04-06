package springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import springdata.jpa.model.Curriculum;
import springdata.jpa.model.QCourse;
import springdata.jpa.model.QCurriculum;

import com.google.common.base.Strings;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.expr.BooleanExpression;

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

	@Override
	public List<Curriculum> searchCurricula(String key, String withCourse) {
		QCurriculum curriculum = QCurriculum.curriculum;
		QCourse course = QCourse.course;
		
		JPQLQuery query = from(curriculum).innerJoin(curriculum.courses, course);
		BooleanExpression conditions = curriculum.title.contains(key).or(curriculum.description.contains(key));
		if(!Strings.isNullOrEmpty(withCourse)) {
			conditions = conditions.and(course.title.contains(withCourse));
		}
		
		List<Curriculum> curricula = query.where(conditions).distinct().list(curriculum);
		return curricula;
	}

}
