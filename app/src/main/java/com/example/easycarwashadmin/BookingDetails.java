package com.example.easycarwashadmin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class BookingDetails extends AppCompatActivity {

    // TextView phone;
    String canId;
    String phoneN;
    ListView listView;
    //FirebaseDatabase firebaseDatabase;
    // DatabaseReference databaseReference;
    FirebaseFirestore fstore;
    FirebaseAuth mAuth;
    private Object Tag;
    private String TAG;
    String userIde;
    private Button sendsms;

    private FirestoreRecyclerAdapter adapter;
    ProgressBar progressBar;

    private Context context;
    String f;
    private Button send;
    private TextView number;
    TextView textView;

    public String disconms;

    SearchView searchView ;
    //------search
    private EditText mSearchField;
    private ImageButton mSearchBtn;
    private RecyclerView mfirestorelist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_booking_details );


//        mSearchField = findViewById( R.id.search_field );
//        mSearchBtn = findViewById( R.id.search_button ) ;
            mfirestorelist = findViewById( R.id.firestore_list );

        // disconms = findViewById( R.id.display_conform_msg );

        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Toast.makeText( this, "User Booking Detalis are Updated", Toast.LENGTH_SHORT ).show();
//        progressBar.setVisibility( View.VISIBLE );

        //query
        Query query = fstore.collection( "Bookings" );

        //recyler
        FirestoreRecyclerOptions<ProductModel> options = new FirestoreRecyclerOptions.Builder<ProductModel>()
                .setQuery( query, ProductModel.class )
                .build();


        adapter = new FirestoreRecyclerAdapter<ProductModel, ProductsviewHolder>( options ) {
            @NonNull
            @Override
            public ProductsviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_item_single, parent, false );

                return new ProductsviewHolder( view );
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsviewHolder holder, int position, @NonNull ProductModel model) {
                holder.mName.setText( model.getName() );
                holder.mDate.setText( model.getDate() );
                holder.mTime.setText( model.getTime() );
                holder.mCarno.setText( model.getCarno() );
                holder.mPhone.setText( model.getPhone() );
                holder.mcanid.setText( model.getCan() );
                holder.phonesms.setText( model.getPhone() );

            }
        };
        mfirestorelist.setHasFixedSize( true );
        mfirestorelist.setLayoutManager( new LinearLayoutManager( this ) );
        mfirestorelist.setAdapter( adapter );


        //------------------------------------------------------------------------
        // cancel


        //------ send no message


        // progressBar.setVisibility( View.GONE );


        //----------------------------------------------------------------------


//        searchView.setOnSearchClickListener( new SearchView() );

    }








    class ProductsviewHolder extends RecyclerView.ViewHolder {

        TextView mName, mDate, mTime, mCarno, mPhone, mcanid, phonesms,txtshow;
        String phone, getString;
        EditText disconms;
        private Button send;

        //----------share
        private static final String SHARED_PREF_NAME = "mysharedpref";
        private static final String KEY_NAME = "keyname";

        EditText editText;
        TextView textView;

        public ProductsviewHolder(@NonNull View itemView) {
            super( itemView );

           // textView = itemView.findViewById( R.id.display_conform_msg );

            mName = itemView.findViewById( R.id.textViewname );
            mDate = itemView.findViewById( R.id.textViewdate );
            mTime = itemView.findViewById( R.id.textViewtime );
            mCarno = itemView.findViewById( R.id.textViewcarno );
            mPhone = itemView.findViewById( R.id.textViewphone );
            mcanid = itemView.findViewById( R.id.cancelid );
            phonesms = itemView.findViewById( R.id.textView2_phonesms );
            send = itemView.findViewById( R.id.button_sms );
            //------share
            editText = findViewById(R.id.editextsms);
            textView = findViewById(R.id.textsmsshow);


            send.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phone = phonesms.getText().toString();
                    Intent sendintent = new Intent( Intent.ACTION_VIEW );
                    sendintent.putExtra( "address", phone );
                    sendintent.putExtra( "sms_body",  "Your Booking You Complete" );
                    sendintent.setType( "vnd.android-dir/mms-sms" );
                    startActivity( sendintent );

                }

            } );
//            saveName();
//            displayName();


        }
//        private void saveName() {
//            String name = editText.getText().toString();
//
//            if (name.isEmpty()) {
//                editText.setError("Name required");
//                editText.requestFocus();
//                return;
//            }
//
//            SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//
//            editor.putString(KEY_NAME, name);
//
//            editor.apply();
//
//            editText.setText("");
//        }

//        private void displayName() {
//            SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//            String name = sp.getString(KEY_NAME, null);
//
//            if (name != null) {
//                textView.setText(name);
//            }
//        }




    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    //----------------------


}




