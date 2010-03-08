package jp.co.softem.ikko.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import jp.co.softem.ikko.core.eis.Employee;

@ManagedBean(eager = true)
@ApplicationScoped
public class EmployeeService implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("ikko-jpa");

	public Employee find(String loginId, String password) {
		EntityManager em = emf.createEntityManager();
		try {
			Object obj = em
					.createQuery(
							"select e from Employee e where e.loginId = :loginId and e.password = :password")
					.setParameter("loginId", loginId).setParameter("password",
							password).getSingleResult();
			return (Employee) obj;
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public Employee findById(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Employee.class, id);
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("select e from Employee e order by e.id")
					.getResultList();
		} finally {
			em.close();
		}
	}

	public void insert(Employee employee) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(employee);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void update(Employee employee) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(employee);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void delete(Employee employee) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(em.getReference(Employee.class, employee.getId()));
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

}
