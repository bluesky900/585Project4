import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

public class Hompage implements Initializable {

    @FXML
    private TextArea post;
    @FXML
    private ComboBox<String> setting;

    private ObservableList<String> settingList = FXCollections.observableArrayList("Add", "Remove", "Hide");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setting.setItems(settingList);
    }
}