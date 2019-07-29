package pl.parser.nbp.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class XMLFilenameValidator {
	private LocalDate dateFrom;
	private LocalDate dateTo;

	public XMLFilenameValidator(LocalDate dateFrom, LocalDate dateTo) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	public boolean isFilenameValid(String filename) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
		int dateFromAsInt = Integer.parseInt(dtf.format(dateFrom));
		int dateToAsInt = Integer.parseInt(dtf.format(dateTo));
		char firstCharFromFilename = filename.charAt(0);
		int dateFromFilename = Integer.parseInt(filename.substring(filename.length() - 6));
		return firstCharFromFilename == 'c' && dateFromFilename >= dateFromAsInt && dateFromFilename <= dateToAsInt;
	}
}