package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	//Os dados como ObservableList da person.
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	public MainApp() {
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Muller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meier"));
		personData.add(new Person("Lydia", "Kurz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Muller"));
	}
	
	/**
	 * Retorna os dados como ObservableList de Person.
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AdressApp");

		initRootLayout();

		showPersonOverview();
	}

	/**
	 * Inicializa o root layout (layout base)
	 */
	public void initRootLayout() {
		try {
			// Carrego o root layout do arquivo FXML.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Mostra o scene contento o root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostra o person overview dentro do root layout.
	 */
	public void showPersonOverview() {
		try {
			//Carrega o person overview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			//Define o person overview dentro do root layout.
			rootLayout.setCenter(personOverview);
			
			//Dá ao controller o acesso a MainApp.
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Abre uma janela para editar detalhes para a pessoa especificada. Se o usuário clicar OK,
	 * as mudanças são salvas no objeto pessoa fornecido e retorna true.
	 * 
	 * @param person O objeto pessoa a ser editado.
	 * @return true se o usuário clicar em OK, caso contrário false.
	 */
	public boolean showPersonEditDialog(Person person) {
		try {
			//Carrega o arquivo FXML e cria um novo stage para a janela popup.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			//Cria o palco dialogStage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			
			//Define a pessoa no Controller.
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);
			
			//Mostra a janela e espera até o usuário fechar.
			dialogStage.showAndWait();
			
			return controller.isOkCliked();
			
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Retorna o palco principal.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
