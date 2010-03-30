/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.softem.ikko.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import jp.co.softem.ikko.eis.Employee;

/**
 * 社員マスタ用のDAOクラスです。
 * 
 * @author yoshikazu
 */
@Stateless
public class EmployeeService extends BaseService<Employee, Integer> {

	private static final long serialVersionUID = 1L;

	public EmployeeService() {
		super(Employee.class);
	}

	/**
	 * ログインIDとパスワードに一致する社員マスタを返答します。
	 * 
	 * <p>
	 * 検索しても見つからない場合はnullを返答します。
	 * </p>
	 * 
	 * @param loginId
	 *            ログインID
	 * @param password
	 *            パスワード
	 * @return 1件の社員マスタ
	 */
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

	/**
	 * 社員IDと指定したイベントIDと一致する情報を返答します。
	 * イベント出欠席マスタにデータがない場合は、社員マスタの情報のみ返答します。
	 * <p>
	 * 検索しても見つからない場合はnullを返答します。
	 * </p>
	 * 
	 * @param eventId
	 *            イベントID
	 * @return 社員No、社員名、イベント出欠席フラグ、イベント出欠席のID
	 */
	public List findByEventId(int eventId) {
		try {
			String sql = "select em.EMPOLYEE_NO, em.EMPOLYEE_NAME, ea.EVENT_ATTENDANCE_FLAG, ea.ID from employee em left outer join event_attendance ea where em.ID = ea.EMPLOYEE_ID and ea.EVENT_ID = ?";
			return em.createNativeQuery(sql).setParameter(1, eventId).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
