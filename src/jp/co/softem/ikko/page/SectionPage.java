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
package jp.co.softem.ikko.page;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import jp.co.softem.ikko.eis.Section;
import jp.co.softem.ikko.service.SectionService;

/**
 * 部署マスタ管理画面用のページクラスです。
 * 
 * @author yoshikazu
 */
@ManagedBean
@RequestScoped
public class SectionPage implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private SectionService service;

	private Section section;

	private List<Section> sectionList;

	/**
	 * 初期処理を実行します。
	 */
	@PostConstruct
	public void init() {
		section = new Section();
		sectionList = service.findAll(true);
	}

	/**
	 * 部署マスタを返答します。
	 * 
	 * @return 部署マスタ
	 */
	public Section getSection() {
		return section;
	}

	/**
	 * 部署マスタの一覧を返答します。
	 * 
	 * @return 部署マスタのリスト
	 */
	public List<Section> getSectionList() {
		return sectionList;
	}

	/**
	 * 部署マスタ用のDAOクラスを設定します。
	 * 
	 * @param service
	 *            部署マスタDAOクラス
	 */
	public void setService(SectionService service) {
		this.service = service;
	}

	/**
	 * 部署マスタを保存します。
	 * 
	 * <ul>
	 * <li>IDが0未満の値が設定された場合は対象情報をデータベースに追加します。</li>
	 * <li>IDに1以上の値が設定された場合は対象情報を更新します。</li>
	 * </ul>
	 * 
	 * @param ae
	 *            イベントクラス
	 */
	public void save(ActionEvent ae) {
		if (section.getId() > 0) {
			// TODO:バリデーション
			service.update(section);
		} else {
			// TODO:バリデーション
			service.insert(section);
		}
		sectionList = service.findAll(true);
	}

	/**
	 * 部署マスタを削除します。
	 * 
	 * @param ae
	 *            イベントクラス
	 */
	public void delete(ActionEvent ae) {
		// TODO:バリデーション
		service.delete(section);
		sectionList = service.findAll(true);
	}

}
