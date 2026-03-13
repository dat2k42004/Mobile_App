package com.example.mobile_app.model;

public class Room implements java.io.Serializable {
    private String id, name, tenantName, phone;
    private double price;
    private boolean isRented; // false: Còn trống, true: Đã thuê

    public Room(String id, String name, double price, boolean isRented, String tenantName, String phone) {
        this.id = id; this.name = name; this.price = price;
        this.isRented = isRented; this.tenantName = tenantName; this.phone = phone;
    }
    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public boolean isRented() { return isRented; }
    public void setRented(boolean rented) { isRented = rented; }
    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
