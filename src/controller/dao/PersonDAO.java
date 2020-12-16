package controller.dao;

import java.util.List;

import model.person.Person;

public class PersonDAO extends JpaDAO<Person> implements GenericDAO<Person> {

	@Override
	public Person create(Person person) {
		return super.create(person);
	}

	@Override
	public Person update(Person person) {
		return super.update(person);
	}

	@Override
	public Person get(Object personID) {
		return super.find(Person.class, personID);
	}

	@Override
	public void delete(Object personID) {
		super.delete(Person.class, personID);
	}

	@Override
	public List<Person> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

}
