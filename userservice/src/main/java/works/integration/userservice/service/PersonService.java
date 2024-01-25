package works.integration.userservice.service;

import works.integration.userservice.entity.Person;

public interface PersonService {

    public Person getPerson(Long id);

    public Person savePerson(Person person);

    public Person updatePerson(Long id, Person person);

    public void deletePerson(Long id);

}
