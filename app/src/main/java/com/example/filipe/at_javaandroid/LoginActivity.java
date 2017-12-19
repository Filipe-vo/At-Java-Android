package com.example.filipe.at_javaandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filipe.at_javaandroid.DAO.FirebaseConfig;
import com.example.filipe.at_javaandroid.Entities.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText textlogin;
    private EditText textpasswordlogin;
    private Button btnLogin;
    private FirebaseAuth autenticacao;
    private Users users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textlogin = (EditText) findViewById(R.id.textlogin);
        textpasswordlogin = (EditText) findViewById(R.id.textpasswordlogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!textlogin.getText().toString().isEmpty() && !textpasswordlogin.getText().toString().isEmpty()){

                    users = new Users();
                    users.setEmail(textlogin.getText().toString());
                    users.setPassword(textpasswordlogin.getText().toString());

                    loginValidate();

                }else {
                    Toast.makeText(LoginActivity.this, "Put a Valid Email and Password.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loginValidate(){

        autenticacao = FirebaseConfig.getAuthenticationFirebase();
        autenticacao.signInWithEmailAndPassword(users.getEmail(), users.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    taskView();
                    Toast.makeText(LoginActivity.this, "Login done successfully.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Email or Pasword are wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void taskView(){
        Intent intent = new Intent(LoginActivity.this, TaskViewActivity.class);
        startActivity(intent);
    }
}
