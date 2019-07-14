package parser.sax;

import entity.Store;
import org.xml.sax.SAXException;
import parser.AbstractParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXDeviceParser extends AbstractParser {

    private static SAXDeviceParser instance;
    private SAXParserHandler saxParserHandler;
    private SAXParserFactory factory;
    private SAXParser parser;
    private Store store;

    public static SAXDeviceParser getInstance() {
        if (instance == null) {
            instance = new SAXDeviceParser();
        }
        return instance;
    }

    private SAXDeviceParser() {
        saxParserHandler = new SAXParserHandler();

        try {
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseDevices(String fileName) {
        try {
            parser.parse(fileName, saxParserHandler);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        deviceStore = saxParserHandler.getStore();
    }

}
