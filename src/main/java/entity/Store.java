package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Store {


    private List<Device> deviceList = new ArrayList<>();

    public List<Device> getDeviceList() {
        return Collections.unmodifiableList(deviceList);
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public void add(Device device) {
        deviceList.add(device);
    }

    public void remove(Device device) {
        deviceList.remove(device);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(deviceList, store.deviceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\tDevices\n");
        for (Device elem : deviceList) {
            builder.append(elem).append("\n");
        }
        return builder.toString();
    }
}
