package com.packtpub.springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.packtpub.springdata.jpa.model.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

}
