package util.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import util.Constant;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class ValidatorXSD {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorXSD.class);

    public boolean validate(String fileName) {
        boolean isValid = false;
        String schemaName = new File(Constant.XSDFILE_PATH).getAbsolutePath();
        Schema schema;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try {
            schema = factory.newSchema(new File(schemaName));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            isValid = parse(parser, fileName);
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.WARN, e);
            isValid = false;
        } catch (org.xml.sax.SAXException e) {
            LOGGER.error(e);
        }

        return isValid;
    }

    private boolean parse(SAXParser parser, String fileName) {
        try {
            parser.parse(fileName, new DeviceErrorHandler());
        } catch (IOException | SAXException e) {
            LOGGER.error(e);
        }
        return true;
    }
}
