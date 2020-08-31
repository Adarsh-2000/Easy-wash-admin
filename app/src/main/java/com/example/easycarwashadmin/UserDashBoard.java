package com.example.easycarwashadmin;
//

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class UserDashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variable
    static final float END_SCALE = 0.7f;
    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView( R.layout.activity_user_dash_board );

        //Hooks
        featuredRecycler = findViewById( R.id.featured_recycler );
        mostViewedRecycler = findViewById( R.id.most_viewed_recycler );
        categoriesRecycler = findViewById( R.id.categories_recycler );

        //Menu Hooks
        drawerLayout = findViewById( R.id.drawer_layout );
        navigationView = findViewById( R.id.navigation_view );

        menuIcon = findViewById( R.id.menu_icon );
        contentView = findViewById( R.id.content );

        //navigation Drawer
        navigationDrawer();


        //internet
        if (isConnectingToInternet( UserDashBoard.this )) {

            //Toast.makeText( getApplicationContext(), "Online", Toast.LENGTH_LONG ).show();
            //Toast toast = Toast.makeText( this,"Online",Toast.LENGTH_SHORT );
            // toast.setGravity( Gravity.CENTER_HORIZONTAL | Gravity.START,90,0 );
            //View toastView = toast.getView();
            // Toast.setBackgroundColor( Color.GREEN);

            //Toast.makeText(UserDashboard.this, "Please Give Feedback...",  3000).show();


            //-------- online message

            // Toast toast= Toast.makeText(getApplicationContext(), "Online", Toast.LENGTH_SHORT);
            //  toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            //  View view = toast.getView();
            //  toast.show();
//---------------------------------online message show end




        } else {
            // Toast toast= Toast.makeText(getApplicationContext(),
            //      "No Internet Connection Please Try Later", Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            //  View view = toast.getView();
            //  toast.show();
            final Dialog dialog = new Dialog(UserDashBoard.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            dialog.setContentView( R.layout.custom_dailog_checkintetnet );
            dialog.show();

            Button btn_enableConnection = dialog.findViewById( R.id.btn_coonection_enable );
            btn_enableConnection.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService( Context.WIFI_SERVICE );
                    wifiManager.setWifiEnabled( true );
                    dialog.dismiss();
                }
            } );
        }

    }

    private boolean isConnectingToInternet(UserDashBoard userDashBoard) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    //navigation drawer function

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener( this );
        // default set
        navigationView.setCheckedItem( R.id.nav_home );

        menuIcon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible( GravityCompat.START ))
                    drawerLayout.closeDrawer( GravityCompat.START );
                else drawerLayout.openDrawer( GravityCompat.START );

            }
        } );

        animateNavigationDrawer();
    }


    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setScrimColor( getResources().getColor( R.color.banner_background_light ) );
        drawerLayout.addDrawerListener( new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX( offsetScale );
                contentView.setScaleY( offsetScale );

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX( xTranslation );
            }
        } );
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible( GravityCompat.START )) {
            drawerLayout.closeDrawer( GravityCompat.START );
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_booking_de:
                Intent bd = new Intent( UserDashBoard.this, BookingDetails.class );
                startActivity( bd );
                break;

            case R.id.nav_nightmode:
                Intent n = new Intent( UserDashBoard.this, NightMode.class );
                startActivity( n );
                break;
            case R.id.nav_notification:
                Intent no = new Intent( UserDashBoard.this, BookingNotifi.class );
                startActivity( no );
                break;

            case R.id.nav_logout:
                Intent lo = new Intent( UserDashBoard.this, Logout.class );
                startActivity( lo );
                break;
            case R.id.nav_share:
                Toast.makeText( this, "Share", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.nav_rate_us:
                Toast.makeText( this, "Rate us Please", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.nav_about:
                Toast.makeText( this, "About us", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.nav_profile:
                Toast.makeText( this, "Profile", Toast.LENGTH_SHORT ).show();
                break;
        }
        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }

}

