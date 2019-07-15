package parser.sax;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import parser.AbstractParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXDeviceParser extends AbstractParser {
    private static final Logger LOGGER = LogManager.getLogger(SAXDeviceParser.class);
    private static SAXDeviceParser instance;
    private SAXParserHandler saxParserHandler;
    private SAXParserFactory factory;
    private SAXParser parser;

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
            LOGGER.error(e);
        }
    }

    @Override
    public void parseDevices(String fileName) {
        try {
            parser.parse(fileName, saxParserHandler);
        } catch (SAXException | IOException e) {
            LOGGER.error(e);
        }
        deviceStore = saxParserHandler.getStore();
    }

}
