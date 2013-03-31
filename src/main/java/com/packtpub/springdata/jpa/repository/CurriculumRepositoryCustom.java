package com.packtpub.springdata.jpa.repository;

import java.util.List;

import com.packtpub.springdata.jpa.model.Curriculum;

public interface CurriculumRepositoryCustom {
	public List<Curriculum> searchCurricula(String key);
}
