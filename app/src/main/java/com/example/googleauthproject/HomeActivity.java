package com.example.googleauthproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity {

    TextView name, mail;
    Button logout;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);
        name=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        logout=findViewById(R.id.logout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount (this);
        if (account!=null){
            String Name = account.getDisplayName();
            String Mail = account.getEmail();

            name.setText(Name);
            mail.setText(Mail);
        }

        logout.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Signout();
            }
        });

    }

    private void Signout() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NotNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

}
