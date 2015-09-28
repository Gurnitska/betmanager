package com.gurnitskaya.bmanager;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import com.gurnitskaya.bmanager.model.BetWrapper;
import com.gurnitskaya.bmanager.view.BetEditDialogController;
import com.gurnitskaya.bmanager.view.BetOverviewController;
import com.gurnitskaya.bmanager.view.RootLayoutController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<BetWrapper> betData = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Bet manager");

        initRootLayout();

        showBetOverview();
    }
    /**
     * Constructor
     */
    public Main() {
        // Add some sample data
        betData.add(new BetWrapper(1, LocalDate.of(1999, 2, 21), "Bundesliga", "Bayer", "Munech", "df", 45, 1.2, 12, "3-0"));
        betData.add(new BetWrapper(2, LocalDate.of(1999, 2, 21), "Seria A", "Milan", "Juventus", "df", 45, 1.55, 12, "1-0"));

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout1.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
         // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the bet overview inside the root layout.
     */
    public void showBetOverview() {
        try {
            // Load bet overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/BetOverview1.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set bet overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            // Give the controller access to the main app.
            BetOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    /**
     * Returns the data as an observable list of Bets. 
     * @return
     */
    public ObservableList<BetWrapper> getBetData() {
        return betData;
    }
    /**
     * Opens a dialog to edit details for the specified bet. If the user
     * clicks OK, the changes are saved into the provided bet object and true
     * is returned.
     * 
     * @param bet the bet object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showBetEditDialog(BetWrapper person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/BetEditDialog1.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Bet");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the bet into the controller.
            BetEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void loadBetsFromExcelFile(File file){
    	
    }
    public static void main(String[] args) {
        launch(args);
    }
}
