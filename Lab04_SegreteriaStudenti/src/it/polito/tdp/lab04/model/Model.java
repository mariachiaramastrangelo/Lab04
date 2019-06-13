package it.polito.tdp.lab04.model;


import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	CorsoDAO cd= new CorsoDAO();
	StudenteDAO sd=new StudenteDAO();
	public List<Corso> getNomiCorsi(){
		return cd.getTuttiICorsi();
	}
	
	public Studente completamentoAutomatico(int matricola) {
		return sd.completamentoAutomatico(matricola);
	}
	public List<Studente> getIscrittiAlCorso(Corso corso){
		return cd.getStudentiIscrittiAlCorso(corso);
	}

	public List <Corso> getCercaCorsiPerStudente(Studente s) {
		// TODO Auto-generated method stub
		return sd.getCercaCorsiPerStudente(s);
	}

	public boolean isIscritto(Studente s, Corso c) {
		return sd.isIscritto(s, c);
	}

	public boolean iscriviStudente(Studente s, Corso c) {
		// TODO Auto-generated method stub
		return cd.iscriviStudenteACorso(s, c);
	}
}
