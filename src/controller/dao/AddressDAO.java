package controller.dao;

import java.util.List;

import model.person.Address;

public class AddressDAO extends JpaDAO<Address> implements GenericDAO<Address> {
	
	public Address create(Address address) {
		return super.create(address);
	}
	
	@Override
	public Address get(Object addressID) {
		return super.find(Address.class, addressID);
	}

	@Override
	public void delete(Object addressID) {
		super.delete(Address.class, addressID);
	}

	@Override
	public List<Address> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
