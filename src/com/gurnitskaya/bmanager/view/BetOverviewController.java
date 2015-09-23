package com.gurnitskaya.bmanager.view;

import java.time.LocalDate;

import org.controlsfx.dialog.Dialogs;

import com.gurnitskaya.bmanager.Main;
import com.gurnitskaya.bmanager.model.BetWrapper;
import com.gurnitskaya.bmanager.util.DateUtil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Dialog for bets overview.
 * 
 * @author Marco Jakob
 */
public class BetOverviewController {
	@FXML
    private TableView<BetWrapper> betTable;
    @FXML
    private TableColumn<BetWrapper, LocalDate> dateColumn;
    @FXML
    private TableColumn<BetWrapper, String> leagueColumn;
    @FXML
    private TableColumn<BetWrapper, String> commandColumn;
    @FXML
    private TableColumn<BetWrapper, String> typeColumn;
    @FXML
    private TableColumn<BetWrapper, Integer> valueColumn;
    @FXML
    private TableColumn<BetWrapper, Double> koefColumn;
    @FXML
    private TableColumn<BetWrapper, String> resultColumn;

    @FXML
    private Label dateLabel;
    @FXML
    private Label leagueLabel;
    @FXML
    private Label homeCommandLabel;
    @FXML
    private Label guestCommandLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label valueLabel;
    @FXML
    private Label koefLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private Label gameResultLabel;
    
    // Reference to the main application.
    private Main main;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BetOverviewController() {
    }
    
    @FXML
    private void initialize() {
        // Initialize the bet table with the seven columns.
    	dateColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, LocalDate>("date"));
    	leagueColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("league"));
    	commandColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("homeCommand"));
    	typeColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("type"));
    	valueColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, Integer>("value"));
    	koefColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, Double>("koef"));
    	resultColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("result"));
    	
        // Clear bet details.
        showBetDetails(null);
        // Listen for selection changes and show the bet details when changed.
        betTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BetWrapper>(){

			@Override
			public void changed(ObservableValue<? extends BetWrapper> arg0, BetWrapper oldValue, BetWrapper newValue) {
				showBetDetails(newValue);
				
			}
        	
        });
    }
    private void showBetDetails(BetWrapper bet) {
        if (bet != null) {
            // Fill the labels with info from the bet object.
            dateLabel.setText(DateUtil.format(bet.getDate()));
            leagueLabel.setText(bet.getLeague());
            homeCommandLabel.setText(bet.getHomeCommand());
            guestCommandLabel.setText(bet.getGuestCommand());
            typeLabel.setText(bet.getType());
            valueLabel.setText(bet.getValue().toString());
            koefLabel.setText(bet.getKoef().toString());
            resultLabel.setText(bet.getResult().toString());
            gameResultLabel.setText(bet.getGameResult());
        } else {
            // Bet is null, remove all the text.
            dateLabel.setText("");
            leagueLabel.setText("");
            homeCommandLabel.setText("");
            guestCommandLabel.setText("");
            typeLabel.setText("");
            valueLabel.setText("");
            koefLabel.setText("");
            resultLabel.setText("");
            gameResultLabel.setText("");
        }
    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        // Add observable list data to the table
        betTable.setItems(main.getBetData());
        
    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteBet() {
        int selectedIndex = betTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            betTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewBet() {
        BetWrapper tempBet = new BetWrapper();
        boolean okClicked = main.showBetEditDialog(tempBet);
        if (okClicked) {
            main.getBetData().add(tempBet);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditBet() {
        BetWrapper selectedPerson = betTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showBetEditDialog(selectedPerson);
            if (okClicked) {
                showBetDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
}
