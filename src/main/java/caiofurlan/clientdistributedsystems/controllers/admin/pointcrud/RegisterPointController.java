package caiofurlan.clientdistributedsystems.controllers.admin.pointcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.pointcrud.SendRegisterPoint;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterPointController implements Initializable {
    @FXML
    private TextField name_field;
    @FXML
    private TextField obs_field;
    @FXML
    private Button register_button;
    @FXML
    private Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        register_button.setOnAction(event -> {
            try {
                onRegister();
            } catch (Exception e) {
                setError_label("Dados inválidos");
                throw new RuntimeException(e);
            }
        });
    }

    private void onRegister() throws Exception {
        String name = name_field.getText();
        String obs = obs_field.getText().isEmpty() ? null : obs_field.getText();
        if (DataValidation.pointInfoValidation(name)) {
            SendRegisterPoint sender = new SendRegisterPoint();
            JsonNode response = sender.send(name, obs);
            if (response != null)
            {
                Receiver receiver = new Receiver(response);
                if (receiver.getError()) {
                    Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
                } else {
                    name_field.setText("");
                    obs_field.setText("");
                    error_label.setText("Ponto cadastrado com sucesso!");
                }
            }
        }
    }

    private void setError_label(String message) {
        error_label.setText(message);
    }
}
