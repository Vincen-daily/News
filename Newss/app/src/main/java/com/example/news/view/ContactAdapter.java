package com.example.news.view;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.model.Contact;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiecy on 2018/02/01.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> contactsList;
    private Context context;

    public ContactAdapter(List<Contact> contactsList,Context context) {
        this.contactsList=contactsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context!=null){
            context=parent.getContext();
        }
        View view=LayoutInflater.from(context).inflate(R.layout.contact_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cursor cursor = null;
        try {

            cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Contact contact=contactsList.get(position);
                    contact.setName(displayName);
                    contact.setNumber(number);
                    contactsList.set(position,contact);
                    holder.name.setText(contactsList.get(position).getName());
                    holder.number.setText(contactsList.get(position).getNumber());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name,number;

        public ViewHolder(View view) {
            super(view);

            name=view.findViewById(R.id.contact_name);
            number=view.findViewById(R.id.contact_number);
        }
    }


//    private void readContacts() {
//        Cursor cursor = null;
//        try {
//
//            cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                    null, null, null, null);
//            if (cursor != null) {
//                while (cursor.moveToNext()) {
//                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    contactsList.add(displayName + "\n" + number);
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//    }
}
