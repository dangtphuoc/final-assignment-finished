package springdata.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.expr.BooleanExpression;

import springdata.jpa.model.ClassOffering;
import springdata.jpa.model.QClassOffering;
import springdata.jpa.model.QCourse;

public class ClassOfferingRepositoryImpl extends QueryDslRepositorySupport implements ClassOfferingRepositoryCustom {

	public ClassOfferingRepositoryImpl() {
		super(ClassOffering.class);
	}
	
	@Override
	public List<ClassOffering> searchClassOfferings(String key, Date startDate, Date endDate) {
		QCourse course = QCourse.course;
		QClassOffering classOffering = QClassOffering.classOffering;
		JPQLQuery query = from(course).innerJoin(course.classOfferings, classOffering);
		
		BooleanExpression conditions = course.title.containsIgnoreCase(key);
		
		if(startDate != null) {
			conditions = conditions.and(classOffering.startTime.goe(startDate));
		}
		
		if(endDate != null) {
			conditions = conditions.and(classOffering.endTime.loe(endDate));
		}
		return query.where(conditions).distinct().list(classOffering);
	}

}
