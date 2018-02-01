package com.example.news;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.news.model.Contact;
import com.example.news.view.ContactAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactActivity extends AppCompatActivity {


    List<Contact> contactsList ;
    private Toolbar toolbar;
    private Context context;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        toolbar = findViewById(R.id.contact_toolbar);
        setSupportActionBar(toolbar);


       recyclerView = findViewById(R.id.contactView);
       // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }


    }

    private void readContacts() {
        ContactAdapter adapter=new ContactAdapter(contactsList,getApplicationContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

//        Cursor cursor = null;
//        try {
//            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                    null, null, null, null);
//            if (cursor != null) {
//                while (cursor.moveToNext()) {
//                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    contactsList.add(displayName + "\n" + number);
//                }
//                adapter.notifyDataSetChanged();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "you denied this", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}

