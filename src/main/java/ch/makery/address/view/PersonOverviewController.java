package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	
	
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label birthdayLabel;
	
	private MainApp mainApp;
	
	/**
	 * Construtor.
	 * O construtor é chamado antes do método inicialize().
	 */
	public PersonOverviewController() {
	}
	
	/**
	 * Inicializa a classe Controller. Esse método é chamado automaticamente
	 * após o arquivo fxml ser carregado.
	 */
	@FXML
	private void initialize() {
		//inicializa a tabela de pessoas com duas colunas.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		//Adiciona os dados da Observablelist na tabela.
		personTable.setItems(this.mainApp.getPersonData());
	}
}
