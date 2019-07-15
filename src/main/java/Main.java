import parser.AbstractParser;
import parser.ParserBuider;

public class Main {

    private static final String filename = "C:\\Users\\Admin\\Desktop\\all\\git\\xml\\src\\main\\resources\\devices.xml";

    public static void main(String[] args) {
        AbstractParser parserBuider = new ParserBuider().createDeviseParser("stax");
        parserBuider.parseDevices(filename);
//        for (int i = 0; i < parserBuider.getDeviceStore().getDeviceList().size(); i++) {
//            System.out.println(parserBuider.getDeviceStore().getDeviceList().get(i));
//        }
//
//        int i = parserBuider.getDeviceStore().getDeviceList().size();
//        System.out.println(i);
    }
}
