package parser.stax;

import parser.AbstractParser;

public class STAXParser extends AbstractParser {
    private static STAXParser instance;

    public static STAXParser getInstance() {
        if (instance == null) {
            instance = new STAXParser();
        }
        return instance;
    }
    @Override
    public void parseDevices(String fileName) {

    }
}
