package com.example.filipe.at_javaandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;


import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btngotologin;
    private Button btnregister;
    private CallbackManager mCallbackManager;
    private FirebaseAuth FirebaseAuth;
    LoginButton loginFacebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);



        btngotologin = (Button) findViewById(R.id.btngotologin);
        btnregister = (Button) findViewById(R.id.btnregister);

        btngotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentgotologin = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentgotologin);

            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentgotoregister = new Intent(MainActivity.this,CadastroActivity.class);
                startActivity(intentgotoregister);
            }
        });

        init();

    }

    private void init() {
        FirebaseAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

        loginFacebook = (LoginButton) findViewById(R.id.btnLoginFacebook);
        loginFacebook.setReadPermissions("email","public_profile");


        loginFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                startActivity(new Intent(getApplicationContext(),TaskViewActivity.class));
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(),"Facebook login failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token){
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getCurrentUser();
                        }else{

                        }
                    }
                });
    }
}
