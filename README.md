The program that collects and displays exchange rates from the National Bank of Poland:
- necessary information for downloading data from the National Bank of Poland on the website: http://www.nbp.pl/home.aspx?f=/kursy/instrukcja_pobierania_kursow_walut.html
- sample XML file containing the courses: http://www.nbp.pl/kursy/xml/c073z070413.xml

The data from the NBP collected is: the purchase price and the sales rate. When running the program, the user provides the following data:
- currency code (USD, EUR, CHF, GBP).
- time interval for which data will be collected - start date and end date.

As a result of the program, the user receives:
- calculated average buying rate for the given data.
- calculated standard deviation of sales rates for given data.

Running the program with sample correct input data:
java pl.parser.nbp.MainClass EUR 2013-01-28 2013-01-31

The correct output for the above example call:
4.1505
0.0125

Description of input and output data:
- EUR <=> currency code
- 2013-01-28 <=> start date
- 2013-01-31 <=> end date
- 4.1505 <=> average buying rate
- 0.0125 <=> standard deviation of sales rates