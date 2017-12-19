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
import com.example.filipe.at_javaandroid.Helpers.Base64Custom;
import com.example.filipe.at_javaandroid.Helpers.Preferences;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class CadastroActivity extends AppCompatActivity implements Validator.ValidationListener {

    Validator validator;

    @NotEmpty(message = "This field is required.\n")
    private EditText Name;

    @NotEmpty(message = "This field is required.\n")
    @com.mobsandgeeks.saripaar.annotation.Email(message = "Put a valid Email.")
    private EditText Email;

    @NotEmpty(message = "This field is required.\n")
    @com.mobsandgeeks.saripaar.annotation.Password (message = "Put a valid Password.")
    private EditText Password;

    @NotEmpty(message = "This field is required.\n")
    @com.mobsandgeeks.saripaar.annotation.ConfirmPassword (message = "The Password Must to equal.")
    private EditText ConfirmPassword;

    @NotEmpty(message = "This field is required.\n")
    private EditText CPF;

    private Button btnGravar;
    private Users users;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        validator = new Validator(this);
        validator.setValidationListener(this);


        Name = (EditText) findViewById(R.id.textname);
        Email = (EditText) findViewById(R.id.textemail);
        Password = (EditText) findViewById(R.id.textpasswordlogin);
        ConfirmPassword = (EditText) findViewById(R.id.textconfirm);
        CPF = (EditText) findViewById(R.id.textcpf);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(CPF, smf);
        CPF.addTextChangedListener(mtw);

        btnGravar = (Button) findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });


    }

    private void register(){

        auth = FirebaseConfig.getAuthenticationFirebase();
        auth.createUserWithEmailAndPassword(
                users.getEmail(),
                users.getPassword()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "User register with succsses", Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(users.getEmail());

                    FirebaseUser userFirebase = task.getResult().getUser();
                    users.setId(identificadorUsuario);
                    users.save();

                    Preferences preferences = new Preferences(CadastroActivity.this);
                    preferences.SalvarUsuariosPreferencias(identificadorUsuario, users.getName());

                    openLogin();
                }else {
                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        erroExcecao = "Must be a strong password!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "Must be a valid E-mail";
                    }catch (Exception e){
                        erroExcecao = "Erro Register";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Erro " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void openLogin(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onValidationSucceeded() {
        if (Password.getText().toString().equals(ConfirmPassword.getText().toString())){

            users = new Users();
            users.setName(Name.getText().toString());
            users.setEmail(Email.getText().toString());
            users.setPassword(Password.getText().toString());
            users.setConfirmPassword(ConfirmPassword.getText().toString());
            users.setCPF(CPF.getText().toString());


            register();

        }else{
            Toast.makeText(CadastroActivity.this, "Password Must to be equals!", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {

                ((EditText) view).setError(message);

            } else {
                Toast.makeText(CadastroActivity.this, message, Toast.LENGTH_LONG).show();
            }

        }

    }
}

