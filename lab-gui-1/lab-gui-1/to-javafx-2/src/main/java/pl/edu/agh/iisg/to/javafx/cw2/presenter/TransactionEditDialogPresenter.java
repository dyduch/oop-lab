package pl.edu.agh.iisg.to.javafx.cw2.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.agh.iisg.to.javafx.cw2.model.Transaction;

public class TransactionEditDialogPresenter {

	private Transaction transaction;

	@FXML
	private TextField dateTextField;

	@FXML
	private TextField payeeTextField;

	@FXML
	private TextField categoryTextField;

	@FXML
	private TextField inflowTextField;
	
	private Stage dialogStage;
	
	private boolean approved;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setData(Transaction transaction) {
		this.transaction = transaction;
		updateControls();
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		// TODO: implement
	}
	
	@FXML
	private void handleCancelAction(ActionEvent event) {
		// TODO: implement
	}
	
	private boolean isInputValid() {
		// TODO: implement
		return true;
	}
	
	private void updateModel() {
		// TODO: implement
	}
	
	private void updateControls() {
		// TODO: implement
	}
}
