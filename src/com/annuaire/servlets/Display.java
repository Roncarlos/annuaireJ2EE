package com.annuaire.servlets;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.annuaire.beans.Annuaire;
import com.annuaire.helpers.XMLSerializerHelper;

/**
 * Servlet implementation class Display
 */
@WebServlet("/Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Annuaire annuaire;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
    }
    
    public void init() throws ServletException {
        super.init();
        this.annuaire = new Annuaire();
        
        

        try {
			this.annuaire.setListOfPerson(XMLSerializerHelper.read(URLDecoder.decode(this.getClass().getClassLoader().getResource("").getPath(), "UTF-8").split(".metadata")[0] + "annuaire/WebContent/WEB-INF/annuaire.xml"));
		} catch (Exception e) {
			// Just do nothing 
			// empty annuaire is already created
		}
        
        // save annauire in servletcontext
        this.getServletContext().setAttribute("annuaire", annuaire);

     }
    
    public void destroy(){
    	// execute the function to save in xml
    	saveState();
    }
    
    public void saveState() {
		try {
			// Serialize annuaire and write in it xml
			// as you can see there is a trick to save
			// in fact to get a absolute local disk link
			// i had to take the actual place of virtual machine
			// which is a temp folder, split the string
			// and add the relative link
			XMLSerializerHelper.write((Annuaire) this.getServletContext().getAttribute("annuaire"), URLDecoder.decode(this.getClass().getClassLoader().getResource("").getPath(), "UTF-8").split(".metadata")[0] + "annuaire/WebContent/WEB-INF/annuaire.xml");
		} catch (Exception e) {
			// print error to stack
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Annuaire annuaire = (Annuaire) this.getServletContext().getAttribute("annuaire");
		
		// This is for reseach, it's done by a post request, but because it's only a search i don't split
		// it to the post method
		String nom 	= request.getParameter("nom") == null ? "" : request.getParameter("nom"),
		prenom 		= request.getParameter("prenom") == null ? "" : request.getParameter("prenom"),
		telephone 	= request.getParameter("telephone") == null ? "" : request.getParameter("telephone"),
		age 		= request.getParameter("age") == null ? "" : request.getParameter("age"),
		adresse 	= request.getParameter("adresse") == null ? "" : request.getParameter("adresse"),
		email 		= request.getParameter("email") == null ? "" : request.getParameter("email"),
		comment 	= request.getParameter("comment") == null ? "" : request.getParameter("comment");
		
		// solution to flash message and delete action
		// to know if we have search something or not
		boolean search = request.getAttribute("search") == null ? true : (boolean) request.getAttribute("search");
		if(!search) {
			email = "";
		}
		
		// Get result of research (if not ask then send all)
		String researchResult = annuaire.searchBy(nom, prenom, age, telephone, adresse, email, comment);
		
		
				
		// set html in request attribute to send it to the jsp
		request.setAttribute("annuaire", researchResult);
		getServletContext().getRequestDispatcher("/WEB-INF/DisplayAnnuaire.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
