package com.gurnitskaya.bmanager.view;

import java.util.ArrayList;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import com.gurnitskaya.bmanager.beans.League;
import com.gurnitskaya.bmanager.beans.League_Command;
import com.gurnitskaya.bmanager.beans.Type;
import com.gurnitskaya.bmanager.dao.LeagueDAO;
import com.gurnitskaya.bmanager.dao.TypeDAO;
import com.gurnitskaya.bmanager.impl.LeagueImplDAO;
import com.gurnitskaya.bmanager.impl.TypeImplDAO;
import com.gurnitskaya.bmanager.model.BetWrapper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<String> leagueField;
    @FXML
    private ComboBox<String> homeCommandField;
    @FXML
    private ComboBox<String> guestCommandField;
    @FXML
    private ComboBox<String> typeField;
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
    private LeagueDAO leagueImpl = new LeagueImplDAO();
    private TypeDAO typeImpl = new TypeImplDAO();
    private List<League> leagues = new ArrayList<>();
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	private void initialize() {
		List<League> leagues = leagueImpl.getAllLeagues();
		for (League league : leagues) {
			this.leagues.add(league);
			leagueField.getItems().add(league.getName());
		}
		List<Type> types = typeImpl.getAllTypes();
		for(Type type: types){
			typeField.getItems().add(type.getName());
		}
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
        if(bet.getBet().getId() == 0){
        	setNewBet(bet);
        } else {
        	setEditableBet(bet);
        }
        dateField.setValue(bet.getDate());
        dateField.setPromptText("dd.mm.yyyy");
        if("".equals(bet.getType()) || bet.getType() == null){
        	typeField.setValue(typeField.getItems().get(0));
        }else{
        	typeField.setValue(bet.getType());
        }

        valueField.setText(bet.getValue().toString());
        koefField.setText(bet.getKoef().toString());
        resultField.setText(bet.getResult().toString());
        scoreField.setText(bet.getScore());
		leagueField.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Object[] lc = leagueImpl.getLeagueByName(newValue).getLeague_command().toArray();
				setCommandFields(lc);
			}
		});
    }
    private void setCommandFields(Object[] lc){
		homeCommandField.getItems().clear();
		guestCommandField.getItems().clear();
		for (Object object : lc) {
			String command = ((League_Command) object).getId().getCommand().getName();
			homeCommandField.getItems().add(command);
			guestCommandField.getItems().add(command);
		}
		homeCommandField.setValue(((League_Command) lc[0]).getId().getCommand().getName());
		guestCommandField.setValue(((League_Command) lc[0]).getId().getCommand().getName());
    }
    private void setEditableBet(BetWrapper bet){
    	leagueField.setValue(bet.getLeague());
    	homeCommandField.setValue(bet.getHomeCommand());
    	guestCommandField.setValue(bet.getGuestCommand());
    }
    
    private void setNewBet(BetWrapper bet){
        Object[] lc = leagues.get(0).getLeague_command().toArray();
        setCommandFields(lc);
    	leagueField.setValue(leagues.get(0).getName());
    	
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
            bet.setLeague(leagueField.getValue());
            bet.setHomeCommand(homeCommandField.getValue());
            bet.setGuestCommand(guestCommandField.getValue());
            bet.setType(typeField.getValue());
            bet.setValue(Integer.parseInt(valueField.getText()));
            bet.setKoef(Double.parseDouble(koefField.getText()));
            bet.setResult(Double.parseDouble(resultField.getText()));
            bet.setScore(scoreField.getText());
            okClicked = true;
            dialogStage.close();
            
        }
        System.out.println(bet);
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
        if (homeCommandField.getValue() == null || homeCommandField.getValue().length() == 0) {
            errorMessage += "No valid home command!\n"; 
        }
        if (guestCommandField.getValue() == null || guestCommandField.getValue().length() == 0) {
            errorMessage += "No valid guest command!\n"; 
        }
        if (typeField.getValue() == null || typeField.getValue().length() == 0) {
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
