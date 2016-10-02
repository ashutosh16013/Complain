package com.ashutosh.iiitd.complain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.*;

public class Logged_in extends AppCompatActivity {

    Button msave_to_internal;
    Button msave_to_external;
    EditText file_name;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        msave_to_internal = (Button)findViewById(R.id.int_storage);
        msave_to_external = (Button)findViewById(R.id.ext_storage);
        file_name = (EditText)findViewById(R.id.file_name);
        text = (EditText)findViewById(R.id.submit_file);
        msave_to_internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = file_name.getText().toString();
                String string = text.getText().toString();
                FileOutputStream outputStream;
                File file= new File(getApplicationContext().getFilesDir(), (filename + ".txt"));
                try {
                    outputStream = openFileOutput((filename+".txt"),getApplicationContext().MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //External Storage
        msave_to_external.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String filename=file_name.getText().toString();
                String data=text.getText().toString();

                FileOutputStream fos;
                try {
                    File myFile = new File("/sdcard/"+filename);
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(data);
                    myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(),filename + " saved",Toast.LENGTH_LONG).show();

                }
                catch (FileNotFoundException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}

            }

        });

    }
}
