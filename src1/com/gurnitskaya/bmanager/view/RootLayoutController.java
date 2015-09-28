package com.gurnitskaya.bmanager.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import com.gurnitskaya.bmanager.Main;

public class RootLayoutController {

	// Reference to the main application
    private Main main;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main main) {
        this.main = main;
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
    
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
