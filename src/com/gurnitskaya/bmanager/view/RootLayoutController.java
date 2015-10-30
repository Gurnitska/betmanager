package com.gurnitskaya.bmanager.view;

import java.io.File;

import com.gurnitskaya.bmanager.Main;
import com.gurnitskaya.bmanager.model.BetWrapper;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

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
    private void handleImportExcel() {
    	FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Excel files (*.xls)", "*.xls");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            main.loadBetsFromExcelFile(file);
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
