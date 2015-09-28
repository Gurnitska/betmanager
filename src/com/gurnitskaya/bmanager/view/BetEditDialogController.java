package com.gurnitskaya.bmanager.view;

import org.controlsfx.dialog.Dialogs;

import com.gurnitskaya.bmanager.model.BetWrapper;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a bets.
 * 
 * @author Olga Gurnitskaya
 */
public class BetEditDialogController {
	enum League{
		SERIA_A("Seria A"), PREMIER("Premier league");
		private String league;
		private League(String league){
			this.league = league;
		}
		public String getLeague(){
			return league;
		}
		public static League getValue(String league){
			for(League value :League.values()){
				if(value.getLeague().equals(league))
					return value;
			}
			return null;
		}
	}
    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<League> leagueField;
    @FXML
    private TextField homeCommandField;
    @FXML
    private TextField guestCommandField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField valueField;
    @FXML
    private TextField koefField;
    @FXML
    private TextField resultField;
    @FXML
    private TextField scoreField;
    
    private Stage dialogStage;
    private BetWrapper bet;
    private boolean okClicked = false;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setBet(BetWrapper bet) {
        this.bet = bet;
        dateField.setValue(bet.getDate());
        dateField.setPromptText("dd.mm.yyyy");
        leagueField.getItems().addAll(League.values());
        if(bet.getLeague() == null){
        	leagueField.setValue(League.PREMIER);
        } else {
        	leagueField.setValue(League.getValue(bet.getLeague()));
        }
        homeCommandField.setText(bet.getHomeCommand());
        guestCommandField.setText(bet.getGuestCommand());
        typeField.setText(bet.getType());
        valueField.setText(bet.getValue().toString());
        koefField.setText(bet.getKoef().toString());
        resultField.setText(bet.getResult().toString());
        scoreField.setText(bet.getScore());
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            bet.setDate(dateField.getValue());
            bet.setLeague(leagueField.getValue().league);
            bet.setHomeCommand(homeCommandField.getText());
            bet.setGuestCommand(guestCommandField.getText());
            bet.setType(typeField.getText());
            bet.setValue(Integer.parseInt(valueField.getText()));
            bet.setKoef(Double.parseDouble(koefField.getText()));
            bet.setResult(Double.parseDouble(resultField.getText()));
            bet.setScore(scoreField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";


        if (dateField.getValue() == null) {
            errorMessage += "No valid date!\n";
        } else {
//            if (!DateUtil.validDate(dateField.getValue())) {
//                errorMessage += "No valid date. Use the format dd.mm.yyyy!\n";
//            }
        }
        if (leagueField.getValue() == null) {
            errorMessage += "No valid league!\n"; 
        }
        if (homeCommandField.getText() == null || homeCommandField.getText().length() == 0) {
            errorMessage += "No valid home command!\n"; 
        }
        if (guestCommandField.getText() == null || guestCommandField.getText().length() == 0) {
            errorMessage += "No valid guest command!\n"; 
        }
        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid bet type!\n"; 
        }
        
        if (valueField.getText() == null || valueField.getText().length() == 0) {
            errorMessage += "No valid bet value!\n"; 
        } else {
            try {
                Integer.parseInt(valueField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid bet value (must be an integer)!\n"; 
            }
        }
        if (koefField.getText() == null || koefField.getText().length() == 0) {
            errorMessage += "No valid koef!\n"; 
        } else {
            try {
                Double.parseDouble(koefField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid koef (must be an double)!\n"; 
            }
        }
        if (resultField.getText() == null || resultField.getText().length() == 0) {
            errorMessage += "No valid result!\n"; 
        } else {
            try {
                Double.parseDouble(resultField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid koef (must be an integer)!\n"; 
            }
        }
        if (scoreField.getText() == null || scoreField.getText().length() == 0) {
            errorMessage += "No valid game result!\n"; 
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                .title("Invalid Fields")
                .masthead("Please correct invalid fields")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
}
