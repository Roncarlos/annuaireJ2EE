package com.annuaire.beans;

import java.util.ArrayList;
import java.util.Comparator;

public class Annuaire {
	private ArrayList<Personne> ListOfPerson = new ArrayList<Personne>();

	public Annuaire() {	}

	public ArrayList<Personne> getListOfPerson() {
		return ListOfPerson;
	}

	public void setListOfPerson(ArrayList<Personne> ap) {
		ListOfPerson = ap;
	}

	public void addNewPerson(Personne p) {
		ListOfPerson.add(p);
	}

	public void deletePerson(Personne p) {
		ListOfPerson.remove(p);
	}

	public Personne getByEmail(String email) {
		for(Personne p : ListOfPerson) {
			if(p.getEmail().equals(email)) {
				return p;
			}
		}
		return new Personne();
	}


	// research by parameter in list of person
	// and return html
	public String searchBy(String nom, String prenom, String age, String telephone, String adresse, String email, String comment) {
		
		// Result after research variable
		
		ArrayList<Personne> resultOfSearch = new ArrayList<Personne>();
		
		// html to send
		
		String html = "";
		
		for(Personne p : ListOfPerson) {
			if(p.getLastName().toLowerCase().contains(nom.toLowerCase()) &&
			p.getFirstName().toLowerCase().contains(prenom.toLowerCase()) &&
			p.getTelNum().toLowerCase().contains(telephone.toLowerCase()) &&
			p.getAdress().toLowerCase().contains(adresse.toLowerCase()) &&
			p.getEmail().contains(email) &&
			p.getAge().contains(age) &&
			p.getComment().toLowerCase().contains(comment.toLowerCase())
			) {
				// Add to arrayList
				resultOfSearch.add(p);
			}
		}
		
		// sort list result by email
		// email = unique identifier
		resultOfSearch.sort(new Comparator<Personne>() {

			public int compare(Personne p1, Personne p2) {
		        return p1.getEmail().compareTo(
			        	p2.getEmail()
			        );
			}
			
		});
		
		// create html of result
		for(Personne p : resultOfSearch) {
			html += "<tr>"
					+ "<td>" + p.getLastName() + "</td>"
					+ "<td>" + p.getFirstName() + "</td>"
					+ "<td>" + p.getAge() + "</td>"
					+ "<td>" + p.getTelNum() + "</td>"
					+ "<td>" + p.getAdress() + "</td>"
					+ "<td>" + p.getEmail() + "</td>"
					+ "<td>" + p.getComment()
					+ "</td>"
					+ "<td> <a class=\"fakeButton\" href=\"/annuaire/modify?email=" + p.getEmail() + "\">Modifier &nbsp</a>"
					+ "<a class=\"fakeButton\" href=\"/annuaire/delete?email=" + p.getEmail() + "\">&nbsp Supprimer</a></td>"
							+ "</tr>";
		}
		
		return html;
	}
}
