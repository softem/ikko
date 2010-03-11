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
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import jp.co.softem.ikko.common.Utils;
import jp.co.softem.ikko.eis.Employee;
import jp.co.softem.ikko.service.EmployeeService;

/**
 * ログイン画面用のページクラスです。
 * 
 * @author yoshikazu
 */
@ManagedBean
@RequestScoped
public class SecurityPage implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final ResourceBundle RB = ResourceBundle
			.getBundle("Messages");

	@EJB
	private EmployeeService service;

	private Employee employee;

	/**
	 * 初期処理を実行します。
	 */
	@PostConstruct
	public void init() {
		employee = new Employee();
	}

	/**
	 * 社員マスタを返答します。
	 * 
	 * @return 社員マスタ
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * 社員マスタ用のDAOクラスを設定します。
	 * 
	 * @param service
	 *            社員マスタDAOクラス
	 */
	public void setService(EmployeeService service) {
		this.service = service;
	}

	/**
	 * ログイン処理を実施します。
	 * 
	 * @return 遷移先(メインメニュー)
	 */
	public String login() {
		Utils.putSession("employee", employee);
		return "main?faces-redirect=true";
	}

	/**
	 * 認証処理を実施します。
	 * 
	 * @param e
	 *            イベントクラス
	 */
	public void validate(ComponentSystemEvent e) {
		UIForm form = (UIForm) e.getComponent();
		String loginId = (String) Utils.getUIInputValue(form, "loginId");
		String password = (String) Utils.getUIInputValue(form, "password");
		Employee emp = service.find(loginId, password);
		if (emp == null) {
			// 認証失敗
			FacesContext fc = FacesContext.getCurrentInstance();
			String message = RB.getString("errors.login.failed");
			fc.addMessage(form.getClientId(), new FacesMessage(message));
			fc.renderResponse();
		}
	}

}
