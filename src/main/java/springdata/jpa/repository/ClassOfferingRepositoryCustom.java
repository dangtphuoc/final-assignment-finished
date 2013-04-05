package springdata.jpa.repository;

import java.util.Date;
import java.util.List;

import springdata.jpa.model.ClassOffering;

public interface ClassOfferingRepositoryCustom {

	public List<ClassOffering> searchClassOfferings(String key, Date startDate, Date endDate);

}
