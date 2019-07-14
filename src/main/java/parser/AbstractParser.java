package parser;

import entity.Store;

public abstract class AbstractParser {

    protected Store deviceStore;

    public AbstractParser() {
        deviceStore = new Store();
    }

    public abstract void parseDevices(String fileName);

    public Store getDeviceStore() {
        return deviceStore;
    }
}
