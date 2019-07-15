package parser;

public enum DeviceEnum {
    DEVICES("devices"),
    ID("id"),
    TYPE_NAME("typeName"),
    ELEMENT("element"),
    NAME("name"),
    ORIGIN("origin"),
    PRICE("price"),
    GROUP("group"),
    TYPE("type"),
    PORT_LIST("port_list"),
    PERIPHERAL("peripheral"),
    HAS_COOLER("has_cooler"),
    PORT("port"),
    ENERGY_USE("energy_use"),
    CRITICAL("critical");

    private String value;

    DeviceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
