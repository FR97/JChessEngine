package fr97.jchess.appfx.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertHelper {

    public static void showAlert(String message, Alert.AlertType type) {
        showAlert("", message, type);
    }

    public static void showAlert(String title, String message, Alert.AlertType type) {
        showAlert(title, "", message, type);
    }

    public static void showAlert(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("JChess Confirm");
        alert.setHeaderText("");
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
    }

}
