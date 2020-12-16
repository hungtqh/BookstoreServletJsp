package controller.dao;

import java.util.List;

import model.customer.CustomerShippingAddress;

public class CustomerShippingAddressDAO extends JpaDAO<CustomerShippingAddress> implements GenericDAO<CustomerShippingAddress> {

	public CustomerShippingAddress create(CustomerShippingAddress shippingAddress) {
		return super.create(shippingAddress);
	}
	
	@Override
	public CustomerShippingAddress get(Object id) {
		return super.find(CustomerShippingAddress.class, id);
	}

	@Override
	public void delete(Object id) {
		super.delete(CustomerShippingAddress.class, id);
	}

	@Override
	public List<CustomerShippingAddress> listAll() {
		return super.findWithNamedQuery("CustomerShippingAddress.findAll");
	}
	
	public CustomerShippingAddress update(CustomerShippingAddress address) {
		return super.update(address);
	}

	@Override
	public long count() {
		return 0;
	}
	
	public List<CustomerShippingAddress> listAllByCustomer(int customerID) {
		return super.findWithNamedQuery("CustomerShippingAddress.findAllByCustomer", "customerID", customerID);
	}

}
