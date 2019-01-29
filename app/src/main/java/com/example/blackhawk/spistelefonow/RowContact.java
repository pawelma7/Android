package com.example.blackhawk.spistelefonow;

public class RowContact {

    private int id;
    private String name;
    private int imageContact;
    private String number;

    public RowContact(int id, String name, int imageContact, String number) {
        this.id = id;
        this.name = name;
        this.imageContact = imageContact;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageContact() {
        return imageContact;
    }

    public void setImageContact(int imageContact) {
        this.imageContact = imageContact;
    }
}
