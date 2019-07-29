package pl.parser.nbp.readers;

import pl.parser.nbp.validators.XMLFilenameValidator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Year;

public class DirectoryReader {
	private static final String DIR_URL_PATTERN = "http://www.nbp.pl/kursy/xml/dir%s.txt";
	private URL dirUrl;
	private BufferedReader in;
	private XMLFilenameValidator validator;

	public DirectoryReader(XMLFilenameValidator validator) {
		dirUrl = null;
		in = null;
		this.validator = validator;
	}

	public String readValidFilename() {
		String filename;
		try {
			while ((filename = in.readLine()) != null)
				if (validator.isFilenameValid(filename))
					return filename;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void openStream(int year) {
		closeStream();
		try {
			dirUrl = new URL(String.format(DIR_URL_PATTERN, year == Year.now().getValue() ? "" : String.valueOf(year)));
			in = new BufferedReader(new InputStreamReader(dirUrl.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeStream() {
		try {
			if (in != null)
				in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}