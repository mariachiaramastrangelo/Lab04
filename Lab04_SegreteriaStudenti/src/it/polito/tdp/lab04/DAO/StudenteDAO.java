package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente completamentoAutomatico(int matricola) {
		final String sql= "SELECT * " + 
				"FROM studente s " + 
				"WHERE s.matricola= ? ";
		Connection conn;
		Studente s = null;
		try {
			conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				s= new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),rs.getString("CDS") );
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return s;
	}

	public List<Corso> getCercaCorsiPerStudente(Studente s) {
		final String sql="SELECT c.codins, c.`crediti`, c.nome, c.`pd` " + 
				"FROM corso c, iscrizione i, studente s " + 
				"WHERE c.`codins`=i.`codins` AND s.`matricola`=i.`matricola` AND s.`matricola`= ? ";
		Connection conn;
		List<Corso> corsiDelloStudente= new LinkedList<Corso>();
		try {
			conn=ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				corsiDelloStudente.add(new Corso(rs.getString("codins"),rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")));
			}
			conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return corsiDelloStudente;
	}

	public boolean isIscritto(Studente s, Corso c) {
		final String sql="SELECT c.* " + 
				"FROM corso c, iscrizione i, studente s " + 
				"WHERE c.`codins`=i.`codins` AND s.`matricola`=i.`matricola` AND s.`matricola`=? AND c.`codins`=? ";
		Connection conn;
		boolean iscritto;
		try {
			conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());
			ResultSet rs= st.executeQuery();
			iscritto=false;
			while(rs.next()) {
				iscritto=true;
			}
			
			
			conn.close();
		}catch(SQLException e ) {
			throw new RuntimeException(e);
		}
		return iscritto;
	}
}
