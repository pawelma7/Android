package com.example.blackhawk.spistelefonow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends Activity {

    EditText etImie,etNazwisko,etNumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etImie = (EditText) findViewById(R.id.editImie);
        etNazwisko = (EditText) findViewById(R.id.editNazwisko);
        etNumer = (EditText) findViewById(R.id.editNumer);
    }

    public void dodaj(View view) {
        DbHandler db;
        db = new DbHandler(this);
        db.addContact(etImie.getText().toString(),etNazwisko.getText().toString(),etNumer.getText().toString());

        Intent intent = new Intent(AddActivity.this,MainActivity.class);
        finish();
        startActivity(intent);

    }

    public void powrot(View view) {
        Intent intent = new Intent(AddActivity.this,MainActivity.class);
        finish();
        startActivity(intent);
    }
}
