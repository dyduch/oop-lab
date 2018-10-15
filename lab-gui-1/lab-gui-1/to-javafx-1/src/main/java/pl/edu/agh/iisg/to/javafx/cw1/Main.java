package pl.edu.agh.iisg.to.javafx.cw1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to.javafx.cw1.controller.AccountOverviewController;
import pl.edu.agh.iisg.to.javafx.cw1.model.generator.DataGenerator;

import java.io.IOException;

public class Main extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My first JavaFX app");

		initRootLayout();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void initRootLayout() {
		try {
			// load layout from FXML file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/AccountOverviewPane.fxml"));
			BorderPane rootLayout = (BorderPane) loader.load();

			//set initial data to controller
			AccountOverviewController controller = (AccountOverviewController) loader.getController();
			controller.setData(DataGenerator.generateAccountData());

			// add layout to a scene and show them all
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// don't do this in common apps
			e.printStackTrace();
		}
	}
}
