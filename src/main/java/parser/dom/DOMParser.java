package parser.dom;

import entity.Device;
import entity.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import parser.AbstractParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMParser extends AbstractParser {
    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);
    private static DOMParser instance;
    private DocumentBuilder documentBuilder;

    private DOMParser() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error(e);
        }
    }

    public static DOMParser getInstance() {
        if (instance == null) {
            instance = new DOMParser();
        }
        return instance;
    }

    @Override
    public void parseDevices(String fileName) {
        Document document = null;
        try {
            document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    Element deviceElement = (Element) node;
                    Device device = initDevice(deviceElement);
                    LOGGER.info(device.toString());
                    deviceStore.add(device);
                }
            }
        } catch (SAXException | IOException e) {
            LOGGER.error(e);
        }
    }

    private Device initDevice(Element deviceElement) {
        long id = Long.parseLong(deviceElement.getAttribute("id"));
        String typeName = deviceElement.getAttribute("typeName");

        String name = getElementTextContent(deviceElement, "name");
        String origin = getElementTextContent(deviceElement, "origin");
        double price = Double.parseDouble(getElementTextContent(deviceElement, "price"));

        String group = getElementTextContent(deviceElement, "group");
        boolean peripheral = Boolean.parseBoolean(getElementTextContent(deviceElement, "peripheral"));
        boolean hasCooler = Boolean.parseBoolean(getElementTextContent(deviceElement, "has_cooler"));
        Type type = new Type(group, peripheral, hasCooler);
        addPorts(deviceElement, type);

        int energyUse = Integer.parseInt(getElementTextContent(deviceElement, "energy_use").trim());
        boolean critical = Boolean.parseBoolean(getElementTextContent(deviceElement, "critical"));
        Device device = new Device(id, typeName, name, origin, price, type, energyUse, critical);
        return device;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private void addPorts(Element deviceElement, Type type) {
        NodeList list = deviceElement.getElementsByTagName("port");
        for (int i = 0; i < list.getLength(); i++) {
            type.addPorts(list.item(i).getTextContent());
        }
    }
}
