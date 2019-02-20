package com.mars.atm.model;

import java.util.ArrayList;
/**存放聯絡人資料*/
public class ContactData {
    private String name;
    private ArrayList<String > phones = new ArrayList<>();

    public ContactData(String name, ArrayList<String> phones) {
        this.name = name;
        this.phones = phones;
    }

    public ContactData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }
}
