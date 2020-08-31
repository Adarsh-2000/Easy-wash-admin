package com.example.easycarwashadmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    ImageView image;
    Button mRLoginBtn,mCreateBtn,forgotTextLink;
    TextInputLayout mEmail,mPassword;
    FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        mRLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);
        forgotTextLink = findViewById(R.id.forgotPassword);



        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),UserDashBoard.class));
            finish();
        }



        mRLoginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Requried");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Requried");
                    return;
                }

                if (password.length() < 6 ){
                    mPassword.setError( "Password Must be >= 6 Characters" );
                    return;
                }

                // for progress bar

                progressBar .setVisibility(View.VISIBLE);

                // authenticate the user
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in Succesfuly",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),UserDashBoard.class));
                        }else {
                            Toast.makeText(Login.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar .setVisibility(View.GONE);


                        }

                    }
                });
            }
        });

        mCreateBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });
        // forgot password link genreate
        forgotTextLink.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),ForgetPassword.class));

            }
        } );
    }
}
