package validator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MyValidator {

	// private static final String XMLPATH =
	// "C:\\Users\\marco\\Dropbox\\TInCAP\\Webinterface\\XML\\XML_20170616\\ambiguitaet_20170616_small.xml";
	// private static final String XSDPATH =
	// "C:\\Users\\marco\\Dropbox\\TInCAP\\Webinterface\\XML\\XSD\\ambiguitaet.xsd";

	
	public static LinkedList<SAXParseException> validateFile(File xmlFile, File xsdFile)
			throws SAXException, IOException {
		// 1. Lookup a factory for the W3C XML Schema language
		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");

		// 2. Compile the schema.
		File schemaLocation = xsdFile;
		Schema schema = factory.newSchema(schemaLocation);

		// 3. Get a validator from the schema.
		Validator validator = schema.newValidator();

		final LinkedList<SAXParseException> exceptions = new LinkedList<SAXParseException>();
		validator.setErrorHandler(new ErrorHandler() {
			@Override
			public void warning(SAXParseException exception) throws SAXException {
				exceptions.add(exception);
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				exceptions.add(exception);
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				exceptions.add(exception);
			}
		});

		// 4. Parse the document you want to check.
		Source source = new StreamSource(xmlFile);

		// 5. Check the document
		try {
			validator.validate(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exceptions;
	}
}