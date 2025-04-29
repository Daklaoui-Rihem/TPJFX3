package com.example;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class AddBookControlor implements Initializable {

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button addBtn;

    @FXML
    private Button exportBtn;

    @FXML
    private Button importBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private Button quitBtn;

    @FXML
    private TableView<Person> table;

    @FXML
    private TableColumn<Person, String> emailCol;

    @FXML
    private TableColumn<Person, String> firstNameCol;

    @FXML
    private TableColumn<Person, String> lastNameCol;

    DataClass a = new DataClass();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setEditable(true);

        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit((EventHandler<CellEditEvent<Person, String>>) new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                Person person = event.getRowValue();
                person.setPrenom(event.getNewValue());
            }
        });

        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                Person person = event.getRowValue();
                person.setNom(event.getNewValue());
            }
        });

        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                Person person = event.getRowValue();
                person.setEmail(event.getNewValue());
            }
        });
    }

    @FXML
    public void quit() {
        System.exit(0);
    }

    public void alertT(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    public static boolean isEmailAdress(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }

    @FXML
    public void add() {
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String email = tfEmail.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || !isEmailAdress(email)) {
            alertT("Please fill all fields correctly.");
            return;
        }

        Person person = new Person(firstName, lastName, email);
        a.getImportList().add(person);

        tfFirstName.clear();
        tfLastName.clear();
        tfEmail.clear();
    }

    @FXML
    public void remove() {
        Person p = table.getSelectionModel().getSelectedItem();
        a.getImportList().remove(p);
    }

    @FXML
    public void importList() {
        table.setItems(a.getImportList());
    }

    @FXML
    public void exportList() {
        ObservableList<Person> selectedItems = table.getSelectionModel().getSelectedItems();
        a.setExportList(selectedItems);
        table.getItems().removeAll(selectedItems);
    }

}
