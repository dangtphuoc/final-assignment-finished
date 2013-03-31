package com.packtpub.springdata.jpa.querydsl.predicate;

import com.mysema.query.collections.MiniApi;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.packtpub.springdata.jpa.model.QCurriculum;

public class CurriculaPredicate {

	public static Predicate searchByTitleAndDescription(String key) {
		QCurriculum curriculum = QCurriculum.curriculum;
		return curriculum.title.contains(key);
	}

}
