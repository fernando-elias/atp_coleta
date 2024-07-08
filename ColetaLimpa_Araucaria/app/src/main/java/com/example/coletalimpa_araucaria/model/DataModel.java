package com.example.coletalimpa_araucaria.model;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }
    public static DataModel getInstance(){
        return instance;
    }
    private ArrayList<Contact> contacts;
    private ContactDataBase dataBase;

    public void createDataBase(Context context){
        dataBase = new ContactDataBase(context);
        contacts = dataBase.getContactsFromDB();
    }

    public ArrayList<Contact> getContacts(){
        return contacts;
    }
    public Contact getContact(int pos){
        return contacts.get(pos);
    }
    public int getContactsSize(){
        return contacts.size();
    }
    public boolean addContact(Contact c){
        long id = dataBase.createContactInDB(c);
        if (id > 0 ){
            c.setId(id);
            contacts.add(c);
            return true;
        }
        return false;
    }
    public boolean insertContact(Contact c, int pos){
        long id = dataBase.insertContactInDB(c);
        if (id > 0 ){
            contacts.add(pos, c);
            return true;
        }
        return false;
    }
    public boolean updateContact(Contact c, int pos){
        int count = dataBase.updateContactInDB(c);
        if (count == 1){
            contacts.set(pos, c);
            return true;
        }
        return false;
    }
    public boolean removeContact(int pos){
        int count = dataBase.removeContactInDB(
                getContact(pos)
        );
        if (count == 1){
            contacts.remove(pos);
            return true;
        }
        return false;
    }
}
