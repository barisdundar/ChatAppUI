package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
private Toolbar actionbar_login;
private EditText txtEmail;
private EditText txtPassword;
private Button btnLogin;
private FirebaseAuth auth;
private FirebaseUser currentUser;
public void init(){
    actionbar_login=(Toolbar) findViewById(R.id.actionbar_login);
    setSupportActionBar(actionbar_login);
    getSupportActionBar().setTitle("Giriş Yap");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    auth=FirebaseAuth.getInstance();
    currentUser=auth.getCurrentUser();
    txtEmail=(EditText) findViewById(R.id.txtEmailLogin1);
    txtPassword=(EditText) findViewById(R.id.txtPasswordLogin1);
    btnLogin=(Button) findViewById(R.id.btnLogin);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        loginUser();
            }
        });
    }

    private void loginUser() {
    String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Lütfen Bir Mail Adresi Giriniz", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Şifre Alanı Boş Olamaz", Toast.LENGTH_LONG).show();
        }
        else {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){//başarılı mı değil mi diye kontrol ediliyor...
                    Toast.makeText(LoginActivity.this, "Giriş Başarılı", Toast.LENGTH_LONG).show();
                    Intent mainintent=new Intent(LoginActivity.this,MainActivity2.class);
                    startActivity(mainintent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Giriş Başarısız", Toast.LENGTH_SHORT).show();
                }
                }
            });
        }
    }
}