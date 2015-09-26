package com.gurn.bmanager.view;

import com.gurn.bmanager.Main;
import com.gurn.bmanager.model.BetWrapper;

import javafx.fxml.FXML;

public class RootLayoutController {
	// Reference to the main application
    private Main main;
    private BetOverviewController controller;
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
        // Add observable list data to the table

    }
    public void setBetOverviewController(BetOverviewController controller){
    	this.controller = controller;
    }
    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        BetWrapper tempBet = new BetWrapper();
        boolean okClicked = main.showBetNewEditDialog(tempBet, "New bet");
        if (okClicked) {
            main.getBetData().add(tempBet);
            main.getBetImpl().addBet(tempBet.getBet());
        }
    }
    @FXML
    private void handleEdit() {
    	controller.handleEditBet();
    }
    @FXML
    private void handleDelete() {
    	controller.handleDeleteBet();
    }


}
