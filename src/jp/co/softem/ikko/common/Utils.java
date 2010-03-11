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
package jp.co.softem.ikko.common;

import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 * 共通ユーティリティクラスです。
 * 
 * @author yoshikazu
 */
public class Utils {

	/**
	 * フラッシュメッセージを追加します。
	 * 
	 * @param key
	 *            キー
	 * @param value
	 *            値
	 */
	public static void addFlush(String key, String value) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.put(key, value);
	}

	/**
	 * 画面から入力された値を返答します。
	 * 
	 * @param form
	 *            フォームオブジェクト
	 * @param name
	 *            項目名
	 * @return 入力値
	 */
	public static Object getUIInputValue(UIForm form, String name) {
		return ((UIInput) form.findComponent(name)).getValue();
	}

	/**
	 * HttpSessionに値を追加します。
	 * 
	 * @param key
	 *            キー
	 * @param value
	 *            値
	 */
	public static void putSession(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(key, value);
	}

}
