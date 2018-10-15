package pl.edu.agh.iisg.to.javafx.cw2;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to.javafx.cw2.controller.AccountAppController;

public class Main extends Application {

	private Stage primaryStage;
	
	private AccountAppController appController;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("My first JavaFX app");

		this.appController = new AccountAppController(primaryStage);
		this.appController.initRootLayout();

	}

	public static void main(String[] args) {
		launch(args);
	}


}
