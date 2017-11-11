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
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Annuaire annuaire = (Annuaire) this.getServletContext().getAttribute("annuaire");
		Personne toModifyOrAdd;
		String email = request.getParameter("email");

		
		// Grab the person from annuaire if the person exist
		// else just get new personne
		if(email != null) {
			toModifyOrAdd = annuaire.getByEmail(email);
		} else {
			toModifyOrAdd = new Personne();
		}
		
		// Add contact and send him to jsp
		request.setAttribute("contact", toModifyOrAdd);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Form.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check every parameter, if empty : no change else change
		Annuaire annuaire = (Annuaire) this.getServletContext().getAttribute("annuaire");
		
		String nom 	= request.getParameter("nom"),
		prenom 		= request.getParameter("prenom"),
		telephone 	= request.getParameter("telephone"),
		age 		= request.getParameter("age"),
		adresse 	= request.getParameter("adresse"),
		email 		= request.getParameter("email"),
		comment 	= request.getParameter("comment");
		
		// Error list
		Map<String, String> errorsMessages = new HashMap<String, String>();
		
		// Je récupère la personne si elle existe, me renvois une personne "vide" sinon
		Personne p  = annuaire.getByEmail(email);
		
		// Si "add" je vérifie que certains champs sont renseignés
		boolean add = p.getEmail().equals("");
		if(add) {
			if(email.equals("")) {
				errorsMessages.put("email", "Le champ e-mail doit être renseigné");
			}
		}
		if(nom.equals("")) {
			errorsMessages.put("nom", "Le champ de Nom doit être renseigné");
		}
		if(prenom.equals("")) {
			errorsMessages.put("prenom", "Le champ de Prénom doit être renseigné");
		}

		if(errorsMessages.size() > 0) {
			// Set errors to request attribute to show it
			// on the jsp
			request.setAttribute("errors", errorsMessages);
		} else {
			// S'il n'y a pas d'erreur on modifie la personne
			p.setLastName(nom);
			p.setFirstName(prenom);
			p.setAdress(adresse);
			p.setAge(age);
			p.setComment(comment);
			p.setTelNum(telephone);
			
			// Si c'est une personne non existante
			// on l'ajoute à l'annuaire
			if(add) {
				// On ajoute à l'annuaire
				p.setEmail(email);
				annuaire.addNewPerson(p);
				
				// display add success
				request.setAttribute("success", "Le contact à bien été ajouté !");
			} else {
				// display modify success 
				request.setAttribute("success", "Le contact à bien été modifié !");
			}

		}
		
		// Resend the get page with updated errors messages
		doGet(request, response);
	}

}
