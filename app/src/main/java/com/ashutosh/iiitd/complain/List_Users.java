package com.ashutosh.iiitd.complain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
public class List_Users extends AppCompatActivity {

    protected final String KEY_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        DBHelper db_obj = new DBHelper(this);
        ArrayList<String> my_users= db_obj.getAllfirst_name();
        String[] arr = new String[5];
        int min = (my_users.size()>5)?5:my_users.size();
        for(int i=0;i<min;i++)
        {
            String k = my_users.get(i).toString();
            arr[i] = k;
        }

        //Populating the list now
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.populate_list, arr);
        ListView listView = (ListView)findViewById(R.id.List_for_users);
        listView.setAdapter(adapter);

        //Now implementing the on click listener for list view

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                String selected = ((TextView) view.findViewById(R.id.label)).getText().toString();

                Intent intent = new Intent(List_Users.this,Data_Manipulate.class);
                intent.putExtra(KEY_USER,selected);
                startActivity(intent);
            }
        });


    }
}
