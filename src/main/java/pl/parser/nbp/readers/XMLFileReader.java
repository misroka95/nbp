package pl.parser.nbp.readers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.net.URL;

public class XMLFileReader {
	private static final String CURRENCY_CODE_TAG_NAME = "kod_waluty";
	private static final String XML_FILE_URL_PATTERN = "http://www.nbp.pl/kursy/xml/%s.xml";
	private String fileUrl;
	private XMLInputFactory inputFactory;
	private XMLStreamReader streamReader;

	public XMLFileReader() {
		inputFactory = XMLInputFactory.newInstance();
		streamReader = null;
	}

	public double[] readRates(String currencyCode) {
		double[] rates = {0.0, 0.0};
		try {
			while (streamReader.hasNext()) {
				streamReader.next();
				if (streamReader.isStartElement()
						&& streamReader.getLocalName().equals(CURRENCY_CODE_TAG_NAME)
						&& streamReader.getElementText().equals(currencyCode)) {
					streamReader.nextTag();
					rates[0] = Double.parseDouble(streamReader.getElementText().replace(",", "."));
					streamReader.nextTag();
					rates[1] = Double.parseDouble(streamReader.getElementText().replace(",", "."));
					return rates;
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return rates;
	}

	public void openStream(String filename) {
		fileUrl = String.format(XML_FILE_URL_PATTERN, filename);
		try {
			streamReader = inputFactory.createXMLStreamReader(new URL(fileUrl).openStream());
		} catch (XMLStreamException | IOException e) {
			e.printStackTrace();
		}
	}

	public void closeStream() {
		try {
			if (streamReader != null)
				streamReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFileUrl() {
		return fileUrl;
	}
}