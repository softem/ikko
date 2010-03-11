package jp.co.softem.ikko.service;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import jp.co.softem.ikko.core.eis.Employee;

@Stateless
public class EmployeeService extends BaseService<Employee> {

	private static final long serialVersionUID = 1L;

	public EmployeeService() {
		super(Employee.class);
	}

	public Employee find(String loginId, String password) {
		try {
			return (Employee) em
					.createQuery(
							"select e from Employee e where e.loginId = :loginId and e.password = :password")
					.setParameter("loginId", loginId).setParameter("password",
							password).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
