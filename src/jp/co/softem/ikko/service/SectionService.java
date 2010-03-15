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

import jp.co.softem.ikko.eis.Section;

/**
 * 部署マスタ用のDAOクラスです。
 * 
 * @author yoshikazu
 */
@Stateless
public class SectionService extends BaseService<Section, Integer> {

	private static final long serialVersionUID = 1L;

	public SectionService() {
		super(Section.class);
	}

	@SuppressWarnings("unchecked")
	public List<Section> listBySectionName(String sectionName) {
		return em.createQuery(
				"select e from Section e where e.sectionName = :sectionName and e.deleteFlag = 0")
				.setParameter("sectionName", sectionName).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Section> listBySectionName(int id, String sectionName) {
		return em
				.createQuery(
						"select e from Section e where e.id <> :id and e.sectionName = :sectionName and e.deleteFlag = 0")
				.setParameter("id", id)
				.setParameter("sectionName", sectionName).getResultList();
	}

}
