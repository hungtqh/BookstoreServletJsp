package controller.dao;

import java.util.Date;
import java.util.List;

import model.customer.Customer;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer>{

	@Override
	public Customer create(Customer customer) {
		customer.setRegisterDate(new Date());
		return super.create(customer);
	}

	@Override
	public Customer update(Customer customer) {
		return super.update(customer);
	}

	@Override
	public Customer get(Object customerID) {
		return super.find(Customer.class, customerID);
	}

	@Override
	public void delete(Object customerID) {
		super.delete(Customer.class, customerID);
	}

	@Override
	public List<Customer> listAll() {
		return super.findWithNamedQuery("Customer.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Customer.countAll");
	}

}
