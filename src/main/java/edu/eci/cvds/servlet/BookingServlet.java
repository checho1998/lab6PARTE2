package edu.eci.cvds.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.eci.cvds.calculator.AirlineCalculator;
import edu.eci.cvds.calculator.BookingCalculator;
import edu.eci.cvds.model.BookingOutput;

import edu.eci.cvds.model.SeatCategory;

/**
 * Servlet class for travel reservations
 */
@WebServlet(urlPatterns = "/booking")
public class BookingServlet extends HttpServlet {

	private static final String PRICE_MESSAGE = "and booking total price is <strong>%.2f</strong>";

	/**
	 * Auto generated serial version id
	 */
	private static final long serialVersionUID = -2768174622692970274L;

	/**
	 * The booking calculator to use
	 */
	private BookingCalculator calculator;

	/**
	 * Constructor to build the servlet and calculator to use
	 */
	public BookingServlet() {
		this.calculator = new AirlineCalculator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer responseWriter = resp.getWriter();

		// TODO Add the corresponding Content Type, Status, and Response
		resp.setContentType("text/html");
		resp.setStatus(200);
		responseWriter.write(readFile("form.html"));
		responseWriter.flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer responseWriter = resp.getWriter();
		SeatCategory Categoria = null; 
		// TODO Create and validate employee
		String sillas = req.getParameter("seats");
		String categoriaSillas = req.getParameter("seatCategory");
		SeatCategory categoria = SeatCategory.valueOf(categoriaSillas);
		int holi=Integer.parseInt(sillas);
		
		BookingOutput response = calculator.calculate(holi, categoria);

		// TODO Add the Content Type, Status, and Response according to calculation response
		resp.setContentType("text/html");
		resp.setStatus(200);
		responseWriter.write(String.format(readFile("result.html"), response.getResult().name(),
				response.getCost().map(cost -> String.format(PRICE_MESSAGE, cost)).orElse("")));
		responseWriter.flush();
	}

	/**
	 * Reads a file from the resources folder
	 * 
	 * @param path The file path
	 * @return the file content
	 * @throws IOException if the file doesn't exist
	 */
	public String readFile(String path) throws IOException {
		StringBuilder html = new StringBuilder();
		try (BufferedReader r = new BufferedReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(path)))) {
			r.lines().forEach(line -> html.append(line).append("\n"));
		}
		return html.toString();
	}

}
