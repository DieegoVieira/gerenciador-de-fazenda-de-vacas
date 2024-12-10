package Fazenda;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomAlert {

    public static void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Substituindo o ícone
        ImageView icon = new ImageView(new Image("file:C:/Users/diego/OneDrive/Documentos/NetBeansProjects/Fazenda/src/main/java/Fazenda/accept-icon.png"));

        icon.setFitHeight(50);
        icon.setFitWidth(50);
        alert.setGraphic(icon);

        alert.showAndWait();
    }
    
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Substituindo o ícone
        ImageView icon = new ImageView(new Image("file:C:/Users/diego/OneDrive/Documentos/NetBeansProjects/Fazenda/src/main/java/Fazenda/error-icon.png"));

        icon.setFitHeight(50);
        icon.setFitWidth(50);
        alert.setGraphic(icon);

        alert.showAndWait();
    }
}
