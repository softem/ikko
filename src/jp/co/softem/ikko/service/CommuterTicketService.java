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

	public JsonResult save(CommuterTicket commuterTicket, ErrorInfo info) {
		JsonResult result = new JsonResult();
		return result;
		
	}
}
