package com.classy.common.model;

public class Garage {
    private String[] Cars;
    private boolean open;
    private String address;
    private String name;

    public Garage() {}

    public Garage(String[] cars, boolean open, String address, String name) {
        Cars = cars;
        this.open = open;
        this.address = address;
        this.name = name;
    }

    public String[] getCars() {
        return Cars;
    }

    public void setCars(String[] cars) {
        Cars = cars;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
