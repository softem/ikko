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

import org.t2framework.t2.action.ErrorInfo;
import org.t2framework.t2.annotation.core.Form;

import jp.co.softem.ikko.core.JsonResult;
import jp.co.softem.ikko.eis.Employee;
import jp.co.softem.ikko.eis.EventInfo;
import jp.co.softem.ikko.eis.Project;

/**
 * イベント用のDAOクラスです。
 * 
 * @author suzuki
 */
@Stateless
public class EventInfoService extends BaseService<EventInfo, Integer> {

	private static final long serialVersionUID = 1L;

	private static final int MAX_RESULT = 200;

	public EventInfoService() {
		super(EventInfo.class);
	}

	/**
	 * 社員IDに一致するイベントを返答します。
	 * 
	 * <p>
	 * 検索しても見つからない場合はnullを返答します。
	 * </p>
	 * 
	 * @param employeeId
	 *            社員ID
	 * @return 社員1人分のイベントデータ
	 */
	public EventInfo find(int employeeId) {
		try {
			// return (EventInfo) em
			// .createQuery(
			// "select e from event_info e, event_attendance ea where substr(e.eventCheckmonth,1,6) = substr(CURDATE() + 0,1,6) and e.Id = ea.eventId and ea.employeeId = :employeeId")
			// .setParameter("employeeId", employeeId).getSingleResult();
			EventInfo eventInfo = null;
			return eventInfo;
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * 適用日とそれ以降のイベントの情報を返答します。
	 * 
	 * <p>
	 * 検索しても見つからない場合はnullを返答します。
	 * </p>
	 * @param eventApplyDate 適用日
	 * @return イベントデータ
	 */
	@SuppressWarnings("unchecked")
	public List<EventInfo> findByApplyDate(Date eventApplyDate) {
		try {
			// String sql = "select e from "
			// + type.getSimpleName()
			// +
			// " e left join e.eventAttendances as ea join ea.employee as em where e.eventApplyDate >= :eventApplyDate and em.id = :employeeId order by e.id desc";
			// return em.createQuery(sql).setParameter("eventApplyDate",
			// eventApplyDate).setParameter("employeeId", employeeId)
			// .setMaxResults(MAX_RESULT).getResultList();
			String sql = "select e from "
					+ type.getSimpleName()
					+ " e where e.eventApplyDate >= :eventApplyDate order by e.id";
			return em.createQuery(sql).setParameter("eventApplyDate",
					eventApplyDate).setMaxResults(MAX_RESULT).getResultList();

		} catch (NoResultException e) {
			return null;
		}
	}

	public JsonResult save(EventInfo eventInfo, ErrorInfo info) {
		JsonResult result = new JsonResult();
		return result;

	}
}
