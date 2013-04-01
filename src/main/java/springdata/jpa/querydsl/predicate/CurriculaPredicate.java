package springdata.jpa.querydsl.predicate;

import springdata.jpa.model.QCurriculum;

import com.mysema.query.types.Predicate;

public class CurriculaPredicate {

	public static Predicate searchByTitleAndDescription(String key) {
		QCurriculum curriculum = QCurriculum.curriculum;
		return curriculum.title.contains(key);
	}

}
