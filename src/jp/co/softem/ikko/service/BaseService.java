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

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAOクラス用の基底クラスです。
 * 
 * @author yoshikazu
 * 
 * @param <T>
 *            Entityクラスの型
 */
public abstract class BaseService<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int MAX_RESULT = 200;

	private Class<T> type;

	@PersistenceContext(unitName = "ikko-jpa")
	protected EntityManager em;

	/**
	 * コンストラクタ。
	 * 
	 * <p>
	 * 操作対象のEntityクラスを指定する必要があります。
	 * <p>
	 * 
	 * @param type
	 *            Entityクラス
	 */
	public BaseService(Class<T> type) {
		this.type = type;
	}

	/**
	 * 指定したIDに一致するEntityを返答します。
	 * 
	 * @param id
	 *            主キー
	 * @return Entity
	 */
	public T findById(Long id) {
		return (T) em.find(type, id);
	}

	/**
	 * Entityの一覧を返答します。
	 * 
	 * <ul>
	 * <li>最大200件まで返答します。</li>
	 * <li>リストはID(昇順)で並んでいます。</li>
	 * </ul>
	 * 
	 * @param deleteFlag
	 *            削除フラグ
	 * @return Entityのリスト
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String sql = "select e from " + type.getSimpleName()
				+ " e order by e.id";
		return em.createQuery(sql).setMaxResults(MAX_RESULT).getResultList();
	}

	/**
	 * Entityの一覧を返答します。
	 * 
	 * <ul>
	 * <li>最大200件まで返答します。</li>
	 * <li>リストはID(昇順)で並んでいます。</li>
	 * <li>削除フラグがtrueの場合は削除フラグがoffの情報のみ返答します。</li>
	 * </ul>
	 * 
	 * @param deleteFlag
	 *            削除フラグ
	 * @return Entityのリスト
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(boolean deleteFlag) {
		String sql = "";
		if (deleteFlag) {
			sql = "select e from " + type.getSimpleName()
					+ " e where e.deleteFlag = 0 order by e.id";
		} else {
			sql = "select e from " + type.getSimpleName() + " e order by e.id";
		}
		return em.createQuery(sql).setMaxResults(MAX_RESULT).getResultList();
	}

	/**
	 * Entityを永続化します。
	 * 
	 * @param o
	 *            永続化するEntity
	 */
	public void insert(T o) {
		em.persist(o);
	}

	/**
	 * Entityを更新します。
	 * 
	 * @param o
	 *            更新するEntity
	 */
	public void update(T o) {
		em.merge(o);
	}

	/**
	 * Entityを削除します。
	 * 
	 * @param o
	 *            削除するEntity
	 */
	public void delete(T o) {
		em.remove(em.merge(o));
	}

}
