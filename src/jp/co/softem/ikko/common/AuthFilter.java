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

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			// String target = ((HttpServletRequest) request).getRequestURI();
			HttpSession session = ((HttpServletRequest) request).getSession();
			// System.out.println(target);
			if (session == null) {
				// /* まだ認証されていない */
				// session = ((HttpServletRequest) request).getSession(true);
				// session.setAttribute("target", target);
				// ((HttpServletResponse) response).sendRedirect("/auth/Login");
			} else {
				// System.out.println(session.getAttribute("employee"));
				// Object loginCheck = session.getAttribute("login");
				// if (loginCheck == null) {
				// /* まだ認証されていない */
				// session.setAttribute("target", target);
				// ((HttpServletResponse) response)
				// .sendRedirect("/auth/Login");
				// }
			}

			chain.doFilter(request, response);
		} catch (ServletException se) {
		} catch (IOException e) {
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
