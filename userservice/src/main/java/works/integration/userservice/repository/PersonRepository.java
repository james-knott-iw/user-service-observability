package works.integration.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import works.integration.userservice.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
