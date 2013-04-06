package springdata.jpa.repository;

import java.util.List;

import springdata.jpa.model.Curriculum;


public interface CurriculumRepositoryCustom {
	public List<Curriculum> searchCurricula(String key);
	public List<Curriculum> searchCurricula(String key, String withCourse);
}
