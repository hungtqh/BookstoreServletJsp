package controller.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDAO<E> {
	private static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("HTStore");
	}
	
	public JpaDAO() {
	}

	public E create(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// begin transaction
		entityManager.getTransaction().begin();

		entityManager.persist(entity); 

		entityManager.flush(); 

		entityManager.refresh(entity); 

		// commit transaction
		entityManager.getTransaction().commit(); 
		entityManager.close();
		
		return entity; 
	}

	// update an entity
	public E update(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// begin transaction
		entityManager.getTransaction().begin();

		// update entity
		entity = entityManager.merge(entity); 
		
		// commit transaction
		entityManager.getTransaction().commit();
		entityManager.close();

		return entity;
	}

	// find(.class, id) will find from db
	public E find(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// begin transaction
		entityManager.getTransaction().begin();

		E entity = entityManager.find(type, id);
		// entityManager.refresh(entity);

		// commit transaction
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return entity;
	}

	public void delete(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		// begin transaction
		entityManager.getTransaction().begin();

		Object reference = entityManager.getReference(type, id); // select from db
		entityManager.remove(reference);

		// commit transaction
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<E> findWithNamedQuery(String queryName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		
		List<E> result = query.getResultList();
		entityManager.close();
		
		return result;
	}
	
	public List<E> findWithNamedQuery(String queryName, int firstResult, int maxResult) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		
		List<E> result = query.getResultList();
		entityManager.close();
		
		return result;
	}

	public List<E> findWithNamedQuery(String queryName, String parameter, Object paraValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(parameter, paraValue);
		
		List<E> result = query.getResultList();
		entityManager.close();
		
		return result;
	}
	
	public List<E> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Set<Entry<String, Object>> setParameters = parameters.entrySet();
		Query query = entityManager.createNamedQuery(queryName);
		
		for (Entry<String, Object> entry : setParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<E> result = query.getResultList();
		entityManager.close();
		
		return result;
	}

	public long countWithNamedQuery(String queryName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		
		long result = (long) query.getSingleResult();
		entityManager.close();
		return result;
	}
	
	public void close() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public long countWithNamedQuery(String queryName, String param, Object paramValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(queryName);
		query.setParameter(param, paramValue);
		
		long result = (long) query.getSingleResult();
		entityManager.close();
		return result;
	}
}
