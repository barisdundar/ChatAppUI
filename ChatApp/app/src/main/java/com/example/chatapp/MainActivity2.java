package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity2 extends AppCompatActivity {
private Toolbar actionbar;
private ViewPager vpmain;
private TabLayout Tabsmain;
private  TabsAdapter tabsAdapter;
private FirebaseAuth auth;
private FirebaseUser CurrentUser;
public void init(){
actionbar=(Toolbar) findViewById(R.id.actionbar);
setSupportActionBar(actionbar);
getSupportActionBar().setTitle(R.string.app_name);
    auth=FirebaseAuth.getInstance();
    CurrentUser=auth.getCurrentUser(); //aktif kullanıcıyı atama
    vpmain=(ViewPager) findViewById(R.id.vpMain);
 tabsAdapter=new TabsAdapter(getSupportFragmentManager());
 vpmain.setAdapter(tabsAdapter);
 Tabsmain=(TabLayout) findViewById(R.id.tabsMain);
 Tabsmain.setupWithViewPager(vpmain);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    @Override
    protected void onStart() {
    if(CurrentUser==null){
        Intent Welcomeintent=new Intent(MainActivity2.this,MainActivity.class);
        startActivity(Welcomeintent);
        finish();
    }
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId()==R.id.mainLogout){
            auth.signOut();
            Intent loginIntent=new Intent(MainActivity2.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();

        }

        return true;
    }
}