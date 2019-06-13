package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;
	
	@FXML
    private TextArea txtResult;
	
  

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCercaNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrizione;
    
    public void setModel(Model model) {
		this.model=model;
		cmbCorsi.getItems().addAll(model.getNomiCorsi());
		
		
	}

    @FXML
    void doCercaNome(ActionEvent event) {
    	Studente s= model.completamentoAutomatico(Integer.parseInt(this.txtMatricola.getText())) ;
    	this.txtNome.setText(s.getNome());
    	this.txtCognome.setText(s.getCognome());
    }

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	
	    	Studente s= new Studente(Integer.parseInt(this.txtMatricola.getText()));
	    	for(Corso c: model.getCercaCorsiPerStudente(s)) {
	    		this.txtResult.appendText(c.toString()+"\n");
	    	}
    	
    }

    @FXML
    void handleCercaIscritti(ActionEvent event) {
    	Corso c= new Corso(this.cmbCorsi.getValue().getCodins());
    	for(Studente s: model.getIscrittiAlCorso(c)) {
    		this.txtResult.appendText(s+"\n");
    	}
    }

    @FXML
    void handleIscrizione(ActionEvent event) {
    	if(!this.txtMatricola.getText().isEmpty() && this.cmbCorsi.getValue().toString()!="") {
    		Studente s= new Studente(Integer.parseInt(this.txtMatricola.getText()));
    		Corso c= new Corso(this.cmbCorsi.getValue().getCodins());
    		if(model.isIscritto(s, c)==true) {
    			this.txtResult.appendText("Lo studente è già iscritto al corso selezionato\n");
    		}else {
    			if(model.iscriviStudente(s, c)==true) {
    				this.txtResult.appendText("Studente correttamente iscritto al corso\n");
    			}else {
    				this.txtResult.appendText("L'iscrizione non è andata a buon fine\n");
    			}
    		}
    	}else {
    		this.txtResult.appendText("Devi inserire una matricola e selezionare un corso\n");
    	}
    }

    @FXML
    void reset(ActionEvent event) {
    	this.txtResult.clear();
    	this.txtNome.clear();
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    	this.cmbCorsi.getSelectionModel().clearSelection();
    }

    @FXML
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrizione != null : "fx:id=\"btnIscrizione\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
        txtResult.setStyle("-fx-font-family: monospace");
    }

	
	
}
