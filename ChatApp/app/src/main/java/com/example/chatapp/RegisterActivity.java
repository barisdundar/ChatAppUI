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

public class RegisterActivity extends AppCompatActivity {
    private Toolbar actionbar_register;
    private EditText txtUsername,txtEmail,txtPassword;
    private Button btnRegister;
    private FirebaseAuth auth;



    public void init(){
        actionbar_register=(Toolbar) findViewById(R.id.actionbar_register);
        setSupportActionBar(actionbar_register);
        getSupportActionBar().setTitle("Hesap Oluştur");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth=FirebaseAuth.getInstance();
        txtUsername=(EditText) findViewById(R.id.txtUsername);
        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        btnRegister=(Button) findViewById(R.id.btnRegister);

    }

    private void createNewAccount() {
        String username=txtUsername.getText().toString();
        String email=txtEmail.getText().toString();
        String password=txtPassword.getText().toString();
       if (TextUtils.isEmpty(email)){
           Toast.makeText(this,"Email Alanı Boş Bırakılamaz",Toast.LENGTH_LONG).show();
       }else if (TextUtils.isEmpty(password)){
               Toast.makeText(this,"Şifre Boş Bırakılamaz",Toast.LENGTH_LONG).show();
           }
       else {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                Toast.makeText(RegisterActivity.this,"Hesabınız Oluşturuldu",Toast.LENGTH_LONG).show();
                Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();//geri tuşu ile register aktivitiye kullanıcı geri dönemeyecek
            }
            else{
                Toast.makeText(RegisterActivity.this, "Bir Hata Oluştu Tekrar Deneyiniz", Toast.LENGTH_LONG).show();
            }

            }
        });
       }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        init();
         btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 createNewAccount();
             }
         });
    }
}