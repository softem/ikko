package jp.co.softem.ikko.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecurityServlet
 */
@WebServlet(urlPatterns = { "/action/*" })
public class UrlDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UrlDispatcher() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String page = getPage(request);
		request.setAttribute("page", "/WEB-INF/jsp" + page + ".jsp");
		request.setAttribute("title", "title" + page.replaceAll("/", "."));
		request.setAttribute("parameterMap", request.getParameterMap());
		request.setAttribute("siteUrl", getSiteUrl(request));
		request.getRequestDispatcher("/template.jsp")
				.forward(request, response);
	}

	private String getPage(HttpServletRequest request) {
		String cp = request.getContextPath();
		return request.getRequestURI().replaceFirst(cp + "/action", "");
	}

	private String getSiteUrl(HttpServletRequest request) {
		int p = request.getServerPort();
		String port = (p == 80) ? "" : ":" + String.valueOf(p);
		return request.getScheme() + "://" + request.getServerName() + port
				+ request.getContextPath();
	}

}
