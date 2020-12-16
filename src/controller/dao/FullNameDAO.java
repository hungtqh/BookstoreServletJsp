package controller.dao;

import java.util.List;

import model.person.FullName;

public class FullNameDAO extends JpaDAO<FullName> implements GenericDAO<FullName> {

	@Override
	public FullName create(FullName FullName) {
		return super.create(FullName);
	}

	@Override
	public FullName update(FullName FullName) {
		return super.update(FullName);
	}

	@Override
	public FullName get(Object FullNameID) {
		return super.find(FullName.class, FullNameID);
	}

	@Override
	public void delete(Object FullNameID) {
		super.delete(FullName.class, FullNameID);
		
	}

	@Override
	public List<FullName> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
