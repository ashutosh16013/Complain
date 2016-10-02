package com.ashutosh.iiitd.complain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;


import java.util.ArrayList;

public class Data_Manipulate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__manipulate);
        Bundle for_data = getIntent().getExtras();
        final String fname = for_data.getString("user");
        final EditText first_name = (EditText)findViewById(R.id.first_name);
        final EditText last_name = (EditText)findViewById(R.id.last_name);
        Button for_update = (Button)findViewById(R.id.delete);
        Button for_delete = (Button)findViewById(R.id.update);
        final DBHelper my_db = new DBHelper(this);
        ArrayList<String> user_detail = new ArrayList<>();
        user_detail = my_db.fetch_data(fname);
        int k = user_detail.size();
        String p = k+"";
        final AlertDialog.Builder my_alert = new AlertDialog.Builder(this);
        //Toast.makeText(getApplicationContext(),k,Toast.LENGTH_SHORT).show();
        first_name.setText(user_detail.get(0));
        last_name.setText(user_detail.get(1));

        //Setting Onclick listener now
        for_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                my_alert.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                my_db.deleteContact(fname);
                                Toast.makeText(getApplicationContext(),"Succesful Deletion",Toast.LENGTH_SHORT).show();
                                Intent my_int = new Intent(Data_Manipulate.this,LoginActivity.class);
                                startActivity(my_int);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        for_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_db.update_user(fname,first_name.getText().toString(),last_name.getText().toString());
                Toast.makeText(getApplicationContext(),"Succesful Updation",Toast.LENGTH_SHORT).show();
                Intent my_int = new Intent(Data_Manipulate.this,LoginActivity.class);
                startActivity(my_int);
            }
        });


    }
}
