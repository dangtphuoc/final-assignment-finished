package springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import springdata.jpa.model.Location;


public interface LocationRepository extends CrudRepository<Location, Long> {

}
