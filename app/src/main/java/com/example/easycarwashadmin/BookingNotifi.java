package com.example.easycarwashadmin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BookingNotifi extends AppCompatActivity {
    private RequestQueue mRequestQue;
    private String URL = "https://fcm.googleapis.com/fcm/send";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_booking_notifi );


        if (getIntent().hasExtra("category")){
            Intent intent = new Intent(BookingNotifi.this,ReceiveNotificationActivity.class);
            intent.putExtra("category",getIntent().getStringExtra("category"));
            intent.putExtra("brandId",getIntent().getStringExtra("brandId"));
            startActivity(intent);
        }


        Button button = findViewById( R.id.btnn );

        mRequestQue = Volley.newRequestQueue(this);
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senNotification();

            }
        } );
    }
    private void senNotification(){
        JSONObject json = new JSONObject();
        try {
            json.put("to","/topics/"+"news");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title","Admin Notification");
            notificationObj.put("body","User Car Booking Booked");

            JSONObject extraData = new JSONObject();
            extraData.put("brandId","Audi");
            extraData.put("category","s7");



            json.put("notification",notificationObj);
            json.put("data",extraData);



            JsonObjectRequest request = new JsonObjectRequest( Request.Method.POST, URL,
                    json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Log.d( "MUR", "onResponse: " );
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("MUR", "onError: "+error.networkResponse);
                }
            }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type","application/json");
                    header.put("authorization","key=AAAAICzVih8:APA91bEauPOtFWfjMIO4AQJfR0Gv0pBOr0MmKjBaEfbTFeKb6sgd5kjaFF4oWwuNHasiVbMEO7PCLMkgcEH1Yu4A2PskBfFqOMTWfjj83JU2e-4gB3m6sEqo7c0tBIshkrT98bUpxg9c" );
                    return header;
                }
            };
            mRequestQue.add(request);
        }
        catch (JSONException e)

        {
            e.printStackTrace();
        }

    }
}
