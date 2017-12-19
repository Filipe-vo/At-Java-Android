package com.example.filipe.at_javaandroid.Entities;

import com.example.filipe.at_javaandroid.DAO.FirebaseConfig;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Filipe on 19/12/2017.
 */

public class Users {

    private String id;
    private String Name;
    private String Email;
    private String Password;
    private String ConfirmPassword;
    private String CPF;

    public Users() {
    }

    public void save(){
        DatabaseReference referenciaFirebase = FirebaseConfig.getFirebase();
        referenciaFirebase.child("Usesr").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMapUser = new HashMap<>();

        hashMapUser.put("id",getId());
        hashMapUser.put("Name",getName());
        hashMapUser.put("Email",getEmail());
        hashMapUser.put("Password",getPassword());
        hashMapUser.put("CPF",getCPF());

        return hashMapUser;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}

