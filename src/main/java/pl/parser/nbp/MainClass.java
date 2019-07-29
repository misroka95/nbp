package pl.parser.nbp;

import pl.parser.nbp.readers.DirectoryReader;
import pl.parser.nbp.readers.XMLFileReader;
import pl.parser.nbp.validators.XMLFilenameValidator;

import java.time.LocalDate;

public class MainClass {
	public static void main(String[] args) {
		String currencyCode = args[0];
		LocalDate dateFrom = LocalDate.parse(args[1]);
		LocalDate dateTo = LocalDate.parse(args[2]);

		double buyingRatesSum = 0.0;
		double salesRatesSum = 0.0;
		double salesRatesSquaresSum = 0.0;
		int ratesCounter = 0;

		DirectoryReader directoryReader = new DirectoryReader(new XMLFilenameValidator(dateFrom, dateTo));
		XMLFileReader xmlFileReader = new XMLFileReader();

		for (int i = dateFrom.getYear(); i <= dateTo.getYear(); i++) {
			directoryReader.openStream(i);
			String filename;
			while ((filename = directoryReader.readValidFilename()) != null) {
				xmlFileReader.openStream(filename);
				double[] rates = xmlFileReader.readRates(currencyCode);
				double currBuyingRate = rates[0];
				double currSalesRate = rates[1];
				xmlFileReader.closeStream();
				buyingRatesSum += currBuyingRate;
				salesRatesSum += currSalesRate;
				salesRatesSquaresSum += Math.pow(currSalesRate, 2);
				ratesCounter++;
			}
			directoryReader.closeStream();
		}
		double buyingRatesAvg = buyingRatesSum / ratesCounter;
		double salesRatesAvg = salesRatesSum / ratesCounter;
		double salesRatesSD = Math.sqrt((salesRatesSquaresSum - 2 * salesRatesAvg * salesRatesSum + ratesCounter * Math.pow(salesRatesAvg, 2)) / ratesCounter);
		System.out.printf("%.4f\n%.4f\n", buyingRatesAvg, salesRatesSD);
	}
}