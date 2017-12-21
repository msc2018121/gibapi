package com.msc.gib;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GIBAPIServlet
 */
@WebServlet("/v1/time")
public class GIBAPIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GIBAPIServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String size = request.getParameter("size");
		response.getWriter().append("{\"Time\":\"" + LocalTime.now() + "\",\"name\":\"top\",\"jso2s\":[");
		long startTime = System.currentTimeMillis();
		int i = 1;
		if ("2".equals(size)) {
			response.getWriter().append(S2Mb.TWO_MB);
		} else if ("5".equals(size)) {
			response.getWriter().append(S5Mb.FIVE_MB);
		} else if ("10".equals(size)) {
			response.getWriter().append(S10Mb.TEN_MB);
		} else if ("20".equals(size)) {
			i=2;
		} else if ("30".equals(size)) {
			i = 3;
		} else if ("40".equals(size)) {
			i = 4;
		} else if ("50".equals(size)) {
			i = 5;
		} else if ("60".equals(size)) {
			i = 6;
		} else if ("70".equals(size)) {
			i = 7;
		} else if ("80".equals(size)) {
			i = 8;
		} else if ("90".equals(size)) {
			i = 9;
		} else if ("100".equals(size)) {
			i = 10;
		} else {
		}

		if (i > 1) {
			for (int j = 0; j < i; j++) {
				if (j > 0) //not 1st
					response.getWriter().append(",");
				response.getWriter().append(S10Mb.TEN_MB);
			}
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;

		response.getWriter().append("]");
		response.getWriter().append(", \"servlet\" : \"" + elapsedTime + "ms\" ");
		response.getWriter().append("}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
