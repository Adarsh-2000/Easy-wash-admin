package com.example.easycarwashadmin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Logout extends AppCompatActivity {
    SharedPreferences prf;
    Intent intent;
    Button btnnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_logout );


/*    // sharedpreference logout so
        TextView result = (TextView)findViewById(R.id.resultView);
        Button btnLogOut = (Button)findViewById(R.id.btnLogOut);
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        intent = new Intent(Logout.this,MainActivity.class);
        result.setText("Hello Please Login Again, "+prf.getString("username",null));


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });*/
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();// logout user  then send to login activity
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}
