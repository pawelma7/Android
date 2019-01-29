package com.example.blackhawk.spistelefonow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TypedArray image;

    DbHandler db;
    ArrayList<Contact> datasList;

    private ListView contactsList;
    List<RowContact> rowContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowContacts = new ArrayList<RowContact>();
        //rowContacts.clear();
        image = getResources().obtainTypedArray(R.array.images);

        db = new DbHandler(this);
        final Cursor datas = db.getData();
        datasList = new ArrayList<Contact>();
        while (datas.moveToNext()){
            datasList.add(new Contact(datas.getInt(0),datas.getString(1),datas.getString(2),datas.getString(3)));
        }

        contactsList = (ListView) findViewById(R.id.lvContacts);




        for (Contact c : datasList) {
            System.out.println("**************");
            System.out.println(c.getName());
            System.out.println(image.getString(0));
            System.out.println("**************");

            rowContacts.add(new RowContact(c.getId(), c.getName() + " " + c.getSurname(), image.getResourceId(0,0), c.getNumber()));

        }

        if (contactsList.getItemAtPosition(1)!=null) {
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), rowContacts);
            contactsList.setAdapter(adapter);
        }




        contactsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                final RowContact rc = (RowContact) contactsList.getItemAtPosition(pos);
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Czy chcesz usunąć kontakt?")
                        .setCancelable(false)
                        .setPositiveButton("Tak, usuń", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //usuniecie kontaktu


                                db.deleteContact(rc.getId());
                                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                return true;
            }
        });


    }


    public void addContact(View view) {
        Intent intent = new Intent(MainActivity.this,AddActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(rowContacts.get(i).getNumber());
        AlertDialog alert = builder.create();

        alert.show();
    }
}
