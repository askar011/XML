package parser.sax;

import entity.Device;
import entity.Store;
import entity.Type;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SAXParserHandler extends DefaultHandler {
    private Device device;
    private String field;
    private Store store;
    private Type type;
    private double aDouble;

    public SAXParserHandler() {
        store = new Store();
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("element")) {
            device = new Device();
            type = new Type();
            device.setType(type);
        }
        field = qName;
        System.out.println(localName);
        if (attributes.getLength() == 2) {
            device.setId(Long.parseLong(attributes.getValue(0)));
            device.setTypeName(attributes.getValue(1));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String info = new String(ch, start, length).trim();
        info = info.replace("\n", "").trim();
        if (field.equalsIgnoreCase("name")) {
            device.setName(info);
        }
        if (field.equalsIgnoreCase("origin")) {
            device.setCountry(info);
        }
        if (field.equals("group")) {
            type.setGroupType(info);
        }
        if (field.equals("peripheral")) {
            type.setPeripheral(Boolean.parseBoolean(info));
        }
        if (field.equals("price")) {
//            System.out.println(getNumber(info));
        }
        if (field.equals("has_cooler")) {
            type.setHasCooler(Boolean.parseBoolean(info));
        }
        if (field.equals("port")) {
            type.addPorts(info);
        }
        if (field.equals("energy_use")) {
//            System.out.println(getNumber(info));
        }
        if (field.equals("critical")) {
            device.setCritical(Boolean.parseBoolean(info));
        }
        store.add(device);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    private double getNumber(String text) {
        double temp = 0;
        Pattern p = Pattern.compile("[0-9]+.[0-9]*|[0-9]*.[0-9]+|[0-9]+");
        Matcher m = p.matcher(text);
        while (m.find()) {
            temp = Double.parseDouble(m.group(0));
        }
        return temp;
    }

    public Store getStore() {
        return store;
    }
}
