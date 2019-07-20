package com.askar.xml.parser.stax;

import com.askar.xml.entity.Device;
import com.askar.xml.entity.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.askar.xml.parser.AbstractParser;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class STAXParser extends AbstractParser {
    private static final Logger LOGGER = LogManager.getLogger(STAXParser.class);
    private static STAXParser instance;

    private STAXParser() {

    }

    public static STAXParser getInstance() {
        if (instance == null) {
            instance = new STAXParser();
        }
        return instance;
    }

    @Override
    public void parseDevices(String fileName) {
        Device device = null;
        Type type = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("element")) {
                        device = new Device();
                        type = new Type();
                        device.setType(type);
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        Attribute typeNameAttr = startElement.getAttributeByName(new QName("typeName"));
                        if (idAttr != null && typeNameAttr != null) {
                            device.setId(Long.parseLong(idAttr.getValue()));
                            device.setTypeName(String.valueOf(typeNameAttr));
                        }
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        device.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("origin")) {
                        xmlEvent = reader.nextEvent();
                        device.setCountry(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("price")) {
                        xmlEvent = reader.nextEvent();
                        device.setPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("group")) {
                        xmlEvent = reader.nextEvent();
                        type.setGroupType(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("peripheral")) {
                        xmlEvent = reader.nextEvent();
                        type.setPeripheral(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("has_cooler")) {
                        xmlEvent = reader.nextEvent();
                        type.setHasCooler(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("port")) {
                        xmlEvent = reader.nextEvent();
                        type.addPorts(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("energy_use")) {
                        xmlEvent = reader.nextEvent();
                        device.setEnergyUse(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("critical")) {
                        xmlEvent = reader.nextEvent();
                        device.setCritical(Boolean.parseBoolean(xmlEvent.asCharacters().getData()));
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("element")) {
                        LOGGER.info(device.toString());
                        deviceStore.add(device);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException exc) {
            LOGGER.error(exc);
        }
    }
}