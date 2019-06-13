package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;


public class CorsoDAO {


	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				
				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				corsi.add(new Corso(codins, numeroCrediti, nome,  periodoDidattico));
			}
			conn.close();
			
			
		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return corsi;
	}
	

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql="SELECT s.* " + 
				"FROM studente s, iscrizione i " + 
				"WHERE s.`matricola`=i.`matricola` AND i.codins= ?";
		List<Studente> studentiIscritti= new LinkedList<Studente>();
		Connection conn;
		try {
			conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				studentiIscritti.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return studentiIscritti;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente s, Corso c) {
		String sql= "INSERT INTO iscrizione(matricola, `codins`) " + 
				"VALUES(?, ?)";
		Connection conn;
		boolean iscrizione;
		try {
			conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());
			int rs= st.executeUpdate();
			if(rs==1) {
				 iscrizione=true;
			}else {
			iscrizione=false;
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return iscrizione;
	}
}
