package parser.sax;

import entity.Device;
import entity.Store;
import entity.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import parser.DeviceEnum;

import java.util.EnumSet;

public class SAXParserHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(SAXParserHandler.class);
    private Device device;
    private Store store;
    private Type type;
    private DeviceEnum currentEnum;
    private EnumSet<DeviceEnum> fields;

    public SAXParserHandler() {
        store = new Store();
        fields = EnumSet.range(DeviceEnum.NAME, DeviceEnum.CRITICAL);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (DeviceEnum.ELEMENT.getValue().equalsIgnoreCase(qName)) {
            device = new Device();
            type = new Type();
            device.setType(type);
        } else {
            DeviceEnum temp = DeviceEnum.valueOf(qName.toUpperCase());
            if (fields.contains(temp)) {
                currentEnum = temp;
            }
        }
        if (attributes.getLength() == 2) {
            device.setId(Long.parseLong(attributes.getValue(0)));
            device.setTypeName(attributes.getValue(1));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            if (currentEnum == DeviceEnum.NAME) {
                device.setName(s);

            } else if (currentEnum == DeviceEnum.ORIGIN) {
                device.setCountry(s);

            } else if (currentEnum == DeviceEnum.PRICE) {
                device.setPrice(Double.parseDouble(s));
            } else if (currentEnum == DeviceEnum.GROUP) {
                type.setGroupType(s);
            } else if (currentEnum == DeviceEnum.PERIPHERAL) {
                type.setPeripheral(Boolean.parseBoolean(s));
            } else if (currentEnum == DeviceEnum.HAS_COOLER) {
                type.setHasCooler(Boolean.parseBoolean(s));
            } else if (currentEnum == DeviceEnum.PORT) {
                type.addPorts(s);
            } else if (currentEnum == DeviceEnum.ENERGY_USE) {
                device.setEnergyUse(Double.parseDouble(s));
            } else if (currentEnum == DeviceEnum.CRITICAL) {
                device.setCritical(Boolean.parseBoolean(s));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("element".equals(qName)) {
            LOGGER.info(device.toString());
            store.add(device);
        }
        currentEnum = null;
    }

    public Store getStore() {
        return store;
    }
}
