package com.gurnitskaya.bmanager.view;

import java.time.LocalDate;

import org.controlsfx.dialog.Dialogs;

import com.gurnitskaya.bmanager.Main;
import com.gurnitskaya.bmanager.model.BetWrapper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
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
    private TableColumn<BetWrapper, String> homeColumn;
    @FXML
    private TableColumn<BetWrapper, String> guestColumn;
    @FXML
    private TableColumn<BetWrapper, String> scoreColumn;
    @FXML
    private TableColumn<BetWrapper, String> typeColumn;
    @FXML
    private TableColumn<BetWrapper, Integer> valueColumn;
    @FXML
    private TableColumn<BetWrapper, Double> koefColumn;
    @FXML
    private TableColumn<BetWrapper, String> resultColumn;

    
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
    	homeColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("homeCommand"));
    	guestColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("guestCommand"));
    	scoreColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("score"));
    	typeColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("type"));
    	valueColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, Integer>("value"));
    	koefColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, Double>("koef"));
    	resultColumn.setCellValueFactory(new PropertyValueFactory<BetWrapper, String>("result"));
    	
        // Clear bet details.
//        showBetDetails(null);
        // Listen for selection changes and show the bet details when changed.
        betTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BetWrapper>(){

			@Override
			public void changed(ObservableValue<? extends BetWrapper> arg0, BetWrapper oldValue, BetWrapper newValue) {
				
			}
        	
        });
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
    public void handleDeleteBet() {
        int selectedIndex = betTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            betTable.getItems().remove(selectedIndex);
            main.getBetImpl().deleteBet(betTable.getSelectionModel().getSelectedItem().getBet());
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
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    public void handleEditBet() {
        BetWrapper bet = betTable.getSelectionModel().getSelectedItem();
        if (bet != null) {
            boolean okClicked = main.showBetNewEditDialog(bet, "Edit bet");
            if (okClicked) {
//            	main.getBetData().add(bet);
            	main.getBetImpl().updateBet(bet.getBet());
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
