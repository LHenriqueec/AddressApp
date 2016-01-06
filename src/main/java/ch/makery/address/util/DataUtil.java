package ch.makery.address.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Funções auxiliares para lidar com datas.
 * 
 * @author Henrique
 *
 */
public class DataUtil {

	// Padrão usado para conversão da Data.
	private static final String DATE_PATTERN = "dd.MM.yyyy";

	// O formatador de datas.
	private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	/**
	 * Retorna os dados como String formatado. O {@link DataUtil#DATE_PATTERN}
	 * (padrão de data) que é utilizado.
	 * 
	 * @param date
	 *            A data a ser retornada como string.
	 * @return String formatado.
	 */
	public static String format(LocalDate date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMATER.format(date);
	}

	
	/**
	 * Converte uma String no formato definido {@link DataUtil#DATE_PATTERN}
	 * para um objeto {@link LocalDate}.
	 * 
	 * Retorna null se o String não puder ser convertido.
	 * 
	 * @param dateString A data como String.
	 * @return O objeto data ou null se não puder ser convertido.
	 */
	public static LocalDate parse(String dateString) {
		try {
			return DATE_FORMATER.parse(dateString, LocalDate::from);
		} catch (DateTimeException e) {
			return null;
		}
	}
	
	/**
	 * Checa se a String é uma data válida.
	 * 
	 * @param dateString A data como String.
	 * @return true se a String é uma data válida.
	 */
	public static boolean validDate(String dateString) {
		//Tenta converter o String.
		return DataUtil.parse(dateString) != null;
	}
}
