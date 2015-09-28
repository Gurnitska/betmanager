package com.gurnitskaya.bmanager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.gurnitskaya.bmanager.beans.Bet;
import com.gurnitskaya.bmanager.impl.BetImplDAO;
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
	static Logger logger = Logger.getLogger(Main.class.getName());
	private BetImplDAO betImpl;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private RootLayoutController rootController;
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
		betImpl = new BetImplDAO();

		List<Bet> bets = betImpl.getAllBets();
		for (Bet bet : bets) {
			betData.add(new BetWrapper(bet));
		}

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
            
            rootController = loader.getController();
            rootController.setMainApp(this);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Sorry, something wrong!", e);
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
            rootController.setBetOverviewController(controller);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Sorry, something wrong!", e);
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
     * Returns the object with works with database. 
     * @return
     */
    public BetImplDAO getBetImpl() {
		return betImpl;
	}
	/**
     * Opens a dialog to edit details for the specified bet. If the user
     * clicks OK, the changes are saved into the provided bet object and true
     * is returned.
     * 
     * @param bet the bet object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showBetNewEditDialog(BetWrapper bet, String title) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/BetEditDialog1.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the bet into the controller.
            BetEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBet(bet);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Sorry, something wrong!", e);
            return false;
        }
    }
    public void loadBetsFromExcelFile(File file){
    	
    }
    public static void main(String[] args) {
    	logger.error("Sorry, something wrong!");
        launch(args);
    }
}
