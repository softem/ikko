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

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import jp.co.softem.ikko.eis.EventAttendance;
import jp.co.softem.ikko.eis.EventInfo;

/**
 * イベント出欠席用のDAOクラスです。
 * 
 * @author suzuki
 */
@Stateless
public class EventAttendanceService extends BaseService<EventAttendance, Integer> {

	private static final long serialVersionUID = 1L;

	private static final int MAX_RESULT = 200;

	public EventAttendanceService() {
		super(EventAttendance.class);
	}

	/**
	 * 指定した社員IDのイベントの情報を返答します。
	 * 
	 * <p>
	 * 検索しても見つからない場合はnullを返答します。
	 * </p>
	 * @param employeeId 社員ID
	 * @return イベント出欠席
	 */
	@SuppressWarnings("unchecked")
	public List<EventAttendance> findByEmployeeId(int employeeId) {
		try {
			String sql = "select e from "
					+ type.getSimpleName()
					+ " e join e.eventInfo ei join e.employee ep where ep.id = :employeeId order by ei.id";
			return em.createQuery(sql).setParameter("employeeId",
					employeeId).setMaxResults(MAX_RESULT).getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}
}
