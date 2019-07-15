package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Type {

    private String groupType;
    private boolean peripheral;
    private boolean hasCooler;

    public Type() {

    }

    public Type(String groupType, boolean peripheral, boolean hasCooler) {
        this.groupType = groupType;
        this.peripheral = peripheral;
        this.hasCooler = hasCooler;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public void setPeripheral(boolean peripheral) {
        this.peripheral = peripheral;
    }

    public void setHasCooler(boolean hasCooler) {
        this.hasCooler = hasCooler;
    }

    private List<String> ports = new ArrayList<>();

    public void addPorts(String args) {
        ports.add(args);
    }

    public String getGroupType() {
        return groupType;
    }

    public boolean isPeripheral() {
        return peripheral;
    }

    public boolean isHasCooler() {
        return hasCooler;
    }

    public List<String> getPorts() {
        return Collections.unmodifiableList(ports);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return peripheral == type.peripheral &&
                hasCooler == type.hasCooler &&
                Objects.equals(groupType, type.groupType) &&
                Objects.equals(ports, type.ports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupType, peripheral, hasCooler, ports);
    }

    @Override
    public String toString() {
        return "Type{" +
                "groupType=" + groupType + "\n" +
                ", peripheral=" + peripheral + "\n" +
                ", hasCooler=" + hasCooler + "\n" +
                ", ports=" + printList() +
                '}' + "\n";
    }

    private String printList() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ports.size(); i++) {
            builder.append(ports.get(i) + ",");
        }
        return builder.toString();
    }
}
