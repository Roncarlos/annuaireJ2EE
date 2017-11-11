package com.annuaire.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.annuaire.beans.Annuaire;
import com.annuaire.beans.Personne;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Annuaire annuaire = (Annuaire) this.getServletContext().getAttribute("annuaire");
		
		// Error list
		Map<String, String> errorsMessages = new HashMap<String, String>();
		
		String email = request.getParameter("email");
		Personne p  = annuaire.getByEmail(email);
		if(!p.getEmail().equals(email)) {
			// set error on flash map
			errorsMessages.put("email", "Cette personne n'existe pas.");
		} else {
			// Delete person
			annuaire.deletePerson(p);
			// set attribute for flash message
			request.setAttribute("success", "La personne a bien été supprimé.");
		}
		
		// flash error messages
		request.setAttribute("errors", errorsMessages);
		
		// delete mail attribute 
		// prevent from searching this email on the
		// display servlet
		request.setAttribute("search", false);
		
		// redirect to display servlet
		// i don't know why but it works with "" or "Display"
		request.getRequestDispatcher("").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
