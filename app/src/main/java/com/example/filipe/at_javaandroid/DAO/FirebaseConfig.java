package com.example.filipe.at_javaandroid.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Filipe on 19/12/2017.
 */

public class FirebaseConfig {

    private static DatabaseReference referenceFirebase;
    private static FirebaseAuth authenticationFirebase;

    public static DatabaseReference getFirebase(){
        if (referenceFirebase == null){
            referenceFirebase = FirebaseDatabase.getInstance().getReference();

        }
        return referenceFirebase;
    }

    public static FirebaseAuth getAuthenticationFirebase(){
        if (authenticationFirebase == null){
            authenticationFirebase = FirebaseAuth.getInstance();

        }
        return authenticationFirebase;

    }

}
