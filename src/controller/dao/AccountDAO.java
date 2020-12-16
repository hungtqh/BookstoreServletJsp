package controller.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.person.Account;

public class AccountDAO extends JpaDAO<Account> implements GenericDAO<Account> {

	public AccountDAO() {
	}
	
	public Account create(Account account) {
		return super.create(account);
	}
	
	public boolean checkLogin(String username, String password) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("username", username);
		parameters.put("password", password);
		
		List<Account> listAccount = super.findWithNamedQuery("Account.checkLogin", parameters);
		
		if (listAccount.size() == 1) {
			return true;
		}
		
		return false;
	}
	
	public Account findByUsername(String username) {
		List<Account> accounts = super.findWithNamedQuery("Account.findByUsername", "username", username);
	
		if (accounts != null && accounts.size() == 1) {
			return accounts.get(0);
		}
		
		return null;
	}
	
	public Account checkIsUsed(int accountID) {
		List<Account> accounts = super.findWithNamedQuery("Account.checkIsUsed", "id", accountID);
		
		if (accounts != null && accounts.size() == 1) {
			return accounts.get(0);
		}
		
		return null;
	}
	
	@Override
	public Account update(Account account) {
		return super.update(account);
	}

	@Override
	public Account get(Object accountId) {
		return super.find(Account.class, accountId);
	}

	@Override
	public void delete(Object accountId) {
		super.delete(Account.class, accountId);
	}

	@Override
	public List<Account> listAll() {
		return super.findWithNamedQuery("Account.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Account.countAll");
	}

	public List<Account> listAllUserAccountAvailable() {
		return super.findWithNamedQuery("Account.findAllUserAccount");
	}	
}
