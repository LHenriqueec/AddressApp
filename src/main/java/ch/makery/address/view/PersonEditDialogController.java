package ch.makery.address.view;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.model.Person;
import ch.makery.address.util.DataUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
	
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField birthdayField;
	
	private Stage dialogStage;
	private Person person;
	private boolean okCliked = false;
	
	/**
	 * Inicializa a classe Controller. Esse método é chamado automaticamente
	 * quando o arquivo FXML for carregado.
	 */
	@FXML
	public void initialize() {
	}
	
	/**
	 * Define o palco desse dialogo.
	 * @param stage dialogStage
	 */
	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}
	
	/**
	 * Define a pessoa a ser editada no dialog.
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		cityField.setText(person.getCity());
		postalCodeField.setText(String.valueOf(person.getPostalCode()));
		birthdayField.setText(DataUtil.format(person.getBirthday()));
		birthdayField.setPromptText("dd.MM.yyyy");
	}
	
	/**
	 * Retorna true se o usuário clicar OK, caso contrário false.
	 * @return
	 */
	public boolean isOkCliked() {
		return okCliked;
	}
	
	/**
	 * Chamado quando o usuário clica em OK.
	 */
	@FXML
	private void handleOk() {
		if(isInputValid()) {
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setCity(cityField.getText());
			person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			person.setBirthday(DataUtil.parse(birthdayField.getText()));
			
			okCliked = true;
			dialogStage.close();
		}
	}
	
	/**
	 * Chamado quando o usuário clica em CANCELAR
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	/**
	 * Valida a entrada do usuário nos campos de texto.
	 * @return true se a entrada é válida.
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		
		if(firstNameField == null || firstNameField.getText().length() == 0) {
			errorMessage += "Nome inválido!\n";
		}
		if(lastNameField == null || lastNameField.getText().length() == 0) {
			errorMessage += "Sobrenome inválido!\n";
		}
		if(streetField == null || streetField.getText().length() == 0) {
			errorMessage += "Endereço inválido!\n";
		}
		if(cityField == null || cityField.getText().length() == 0) {
			errorMessage += "Cidade inválida!\n";
		}
		if(postalCodeField == null || postalCodeField.getText().length() == 0) {
			errorMessage += "Código Postal inválido!\n";
		} else {
			//Tenta converter o Código Postal em um int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch(NumberFormatException e) {
				errorMessage += "Código Postal inválido (deve ser um inteiro)!\n";
			}
		}
		if(birthdayField == null || birthdayField.getText().length() == 0) {
			errorMessage += "Aniversário inválido!\n";
		}  else {
			if(!DataUtil.validDate(birthdayField.getText())) {
				errorMessage += "Aniversário inválido. Use o formato dd.mm.yyyy!\n";
			}
		}
		
		if(errorMessage.length() == 0) {
			return true;
		} else {
			//Mostra a mesnagem de erro.
			Dialogs.create()
			.title("Campos inválidos")
			.masthead("Por favor, corrija os campos inválidos")
			.message(errorMessage)
			.showError();
			return false;
		}
	}
}
