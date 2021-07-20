package com.lesan.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	public Label welcomeLable;
	public ChoiceBox<String> choiceBox;
	public TextField userInput;
	public Button convertButton;

	private static final String C_TO_F = "Celsius to Fahrenheit";
	private static final String F_TO_C = "Fahrenheit to Celsius";

	private boolean isC_TO_F = false;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		choiceBox.getItems().add(C_TO_F);
		choiceBox.getItems().add(F_TO_C);
		choiceBox.setValue(C_TO_F);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
			if(t1.equals(C_TO_F)) {
				isC_TO_F = true;
			} else {
				isC_TO_F = false;
			}
		});

		convertButton.setOnAction(actionEvent -> {
			convert();
		});
	}

	private void convert() {
		String input = userInput.getText();
		float enteredTemperature = 0.0f;
		try {
			enteredTemperature = Float.parseFloat(input);
		} catch (Exception ex) {
			warnUser();
			return;
		}
		float newTemperature = 0.0f;
		if(isC_TO_F) {
			newTemperature = (enteredTemperature * 9/5) + 32;
		} else {
			newTemperature = (enteredTemperature - 32) * 5 / 9;
		}
		display(newTemperature);
	}

	private void warnUser() {

		Alert warnAlert = new Alert(Alert.AlertType.ERROR);
		warnAlert.setTitle("Error Occurred");
		warnAlert.setHeaderText("Invalid Temperature Entered");
		warnAlert.setContentText("Please Enter Valid Temperature");
		warnAlert.show();

	}

	private void display(float newTemperature) {
		String unit = isC_TO_F? "F" : "C";
		System.out.println();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new Temperature is: "+newTemperature+" "+unit);
		alert.show();
	}
}
