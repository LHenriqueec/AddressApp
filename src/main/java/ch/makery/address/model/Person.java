package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe Model para uma Person (Pessoa).
 * @author Henrique
 *
 */
public class Person {
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty street;
	private StringProperty city;
	private IntegerProperty postalCode;
	private ObjectProperty<LocalDate> birthday;

	/**
	 * Construtor padrão.
	 */
	public Person() {
		this(null, null);
	}
	
	/**
	 * Construtor com alguns dados iniciais
	 * 
	 * @param farstName Primeiro nome da Pessoa.
	 * @param lastName Sobrenome da Pessoa.
	 */
	public Person(String farstName, String lastName) {
		this.firstName = new SimpleStringProperty(farstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		//Alguns dados de exemplo, apenas para testes.
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	public String getStreet() {
		return this.street.get();
	}
	
	public void setStreet(String street) {
		this.street.set(street);
	}
	
	public StringProperty streetProperty() {
		return street;
	}
	
	public String getCity() {
		return city.get();
	}
	
	public void setCity(String city) {
		this.city.set(city);
	}
	
	public StringProperty cityProperty() {
		return city;
	}
	
	public int getPostalCode() {
		return postalCode.get();
	}
	
	public void setPostalCode(int postalCode) {
		this.postalCode.set(postalCode);
	}
	
	public IntegerProperty postalCodeProperty() {
		return postalCode;
	}
	
	public LocalDate getBirthday() {
		return birthday.get();
	}
	
	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}
	
	public ObjectProperty<LocalDate> birthdayProperty() {
		return birthday;
	}
}
