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

import org.t2framework.t2.action.ErrorInfo;
import org.t2framework.t2.navigation.Json;

import jp.co.softem.ikko.core.JsonResult;
import jp.co.softem.ikko.eis.CommuterTicket;

/**
 * 定期代トラン用のDAOクラスです。
 * 
 * @author kazunari
 */
@Stateless
public class CommuterTicketService extends BaseService<CommuterTicket, Integer> {

	private static final long serialVersionUID = 1L;

	public CommuterTicketService() {
		super(CommuterTicket.class);
	}

	@SuppressWarnings("unchecked")
	public List<CommuterTicket> listBySectionName(String sectionName) {
		return em.createQuery(
				"select e from Section e where e.sectionName = :sectionName and e.deleteFlag = 0")
				.setParameter("sectionName", sectionName).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CommuterTicket> listBySectionName(int id, String sectionName) {
		return em
				.createQuery(
						"select e from Section e where e.id <> :id and e.sectionName = :sectionName and e.deleteFlag = 0")
				.setParameter("id", id)
				.setParameter("sectionName", sectionName).getResultList();
	}

	public JsonResult save(CommuterTicket commuterTicket, ErrorInfo info) {
		JsonResult result = new JsonResult();
/*
		int id = project.getId();
		String projectName = project.getSectionName();
		if (project.getId() > 0) {
			if (projectName == null || projectName.length() == 0) {
				result.put("projectName", "message.required");
			}
			List<Project> finded = this.listBySectionName(id, projectName);
			if (finded.size() > 0) {
				result.put("projectName", "message.exists");
			}
			if (!result.isError()) {
				this.update(project);
			}project
		} else {
			if (projectName == null || projectName.length() == 0) {
				result.put("projectName", "message.required");
			}
			List<Project> finded = this.listBySectionName(projectName);
			if (finded.size() > 0) {
				result.put("projectName", "message.exists");
			}
			if (!result.isError()) {
				this.insert(project);
			}
		}
*/
		this.insert(commuterTicket);
		return result;
		
	}

	public JsonResult deleteLogical(CommuterTicket commuterTicket, ErrorInfo info) {
		JsonResult result = new JsonResult();
		this.delete(commuterTicket.getId());
		return result;
	}
}
