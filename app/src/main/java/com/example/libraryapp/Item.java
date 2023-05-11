package com.example.libraryapp;

public class Item {
    private String name, details, state;
    private String lessee_name, lessee_phone;
    private String amount;

    public Item(String name, String amount, String details, String state, String lessee_name, String lessee_phone) {
        this.name = name;
        this.amount = amount;
        this.details = details;
        this.state = state;
        this.lessee_name = lessee_name;
        this.lessee_phone = lessee_phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLessee_name() {
        return lessee_name;
    }

    public void setLessee_name(String lessee_name) {
        this.lessee_name = lessee_name;
    }

    public String getLessee_phone() {
        return lessee_phone;
    }

    public void setLessee_phone(String lessee_phone) {
        this.lessee_phone = lessee_phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "[name=" +  name + ", amount=" + amount + ", details=" + details + ", state: " + state + ", lessee_name=" + lessee_name + ", lessee_phone=" + lessee_phone + "]";
    }

    public static Item fromString(String str) {
        str = str.substring(1, str.length() - 1); // remove os colchetes da string
        String[] parts = str.split(", ");

        String name = parts[0].substring(parts[0].indexOf("=") + 1);
        String amount = parts[1].substring(parts[1].indexOf("=") + 1);
        String details = parts[2].substring(parts[2].indexOf("=") + 1);
        String state = parts[3].substring(parts[3].indexOf("=") + 1);
        String lessee_name = parts[4].substring(parts[4].indexOf("=") + 1);
        String lessee_phone = parts[5].substring(parts[5].indexOf("=") + 1);

        return new Item(name, amount, details, state, lessee_name, lessee_phone);
    }
}
