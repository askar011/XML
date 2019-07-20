package com.askar.xml.entity;

import java.util.Objects;

public class Device {

    private long id;
    private String typeName;
    private String name;
    private String country;
    private double price;
    private Type type;
    private double energyUse;
    private boolean critical;

    public Device() {

    }

    public Device(long id, String typeName, String name, String country, double price,
                  Type type, double energyUse, boolean critical) {
        this.id = id;
        this.typeName = typeName;
        this.name = name;
        this.country = country;
        this.price = price;
        this.type = type;
        this.energyUse = energyUse;
        this.critical = critical;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setEnergyUse(double energyUse) {
        this.energyUse = energyUse;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getCountry() {
        return country;
    }

    public double getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public double getEnergyUse() {
        return energyUse;
    }

    public boolean isCritical() {
        return critical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id == device.id &&
                Double.compare(device.price, price) == 0 &&
                Double.compare(device.energyUse, energyUse) == 0 &&
                critical == device.critical &&
                Objects.equals(typeName, device.typeName) &&
                Objects.equals(name, device.name) &&
                Objects.equals(country, device.country) &&
                Objects.equals(type, device.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName, name, country, price, type, energyUse, critical);
    }


    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", energyUse=" + energyUse +
                ", critical=" + critical +
                '}';
    }
}
