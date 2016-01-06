package ch.makery.address.view;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DataUtil;
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
		
		//Limpa os detalhes da pessoa.
		showPersonDatail(null);
		
		//Detecta mudanças de seleção e mostra os detalhes da pessoa quando houver.
		personTable.getSelectionModel().selectedItemProperty().
			addListener((observable, oldValue, newValue) -> showPersonDatail(newValue));
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		//Adiciona os dados da Observablelist na tabela.
		personTable.setItems(this.mainApp.getPersonData());
	}
	
	private void showPersonDatail(Person person) {
		//Preenche as labels com informações do objeto person
		if(person != null) {
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			cityLabel.setText(person.getCity());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			birthdayLabel.setText(DataUtil.format(person.getBirthday()));
			
			
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			cityLabel.setText("");
			postalCodeLabel.setText("");
			birthdayLabel.setText("");
		}
	}
	
	/**
	 * Chamado quando o usuário clica no botão delete.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectIndex = personTable.getSelectionModel().getSelectedIndex();
		
		if(selectIndex >= 0) {
			personTable.getItems().remove(selectIndex);
		} else {
			//Nada selecionado.
			Dialogs.create()
				.title("Nenhuma seleção")
				.masthead("Nenhuma Pessoa Selecionada")
				.message("Por favor, selecione uma pessoa na tabela")
				.showWarning();
		}
	}
}
