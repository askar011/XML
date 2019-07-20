package com.askar.xml.util.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DeviceErrorHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(DeviceErrorHandler.class);

    @Override
    public void warning(SAXParseException e) throws SAXParseException {
        LOGGER.log(Level.WARN, getLineAddress(e) + e);
        throw new SAXParseException(e.getMessage(), null);
    }

    @Override
    public void error(SAXParseException e) throws SAXParseException {
        LOGGER.log(Level.ERROR, getLineAddress(e) + e);
        throw new SAXParseException(e.getMessage(), null);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXParseException {
        LOGGER.log(Level.FATAL, getLineAddress(e) + e);
        throw new SAXParseException(e.getMessage(), null);
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + ":" + e.getColumnNumber() + " ";
    }
}
