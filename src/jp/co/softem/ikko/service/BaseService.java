package jp.co.softem.ikko.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseService<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int MAX_RESULT = 100;

	private Class<T> type;

	@PersistenceContext(unitName = "ikko-jpa")
	protected EntityManager em;

	public BaseService(Class<T> type) {
		this.type = type;
	}

	public T findById(Long id) {
		return (T) em.find(type, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return em.createQuery(
				"select e from " + type.getSimpleName() + " e order by e.id")
				.setMaxResults(MAX_RESULT).getResultList();
	}

	public void insert(T o) {
		em.persist(o);
	}

	public void update(T o) {
		em.merge(o);
	}

	public void delete(T o) {
		em.remove(o);
	}

}
