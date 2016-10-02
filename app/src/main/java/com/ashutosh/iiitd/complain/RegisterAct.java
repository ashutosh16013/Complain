package com.ashutosh.iiitd.complain;

import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;


public class RegisterAct extends AppCompatActivity {

    private EditText mfname;
    private EditText mpass;
    private EditText mlname;
    private EditText memail;
    SharedPreferences sharedpref;
    public static final String MyPREF = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Email = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Declared final as it needs to be accessed inside inner class
        final DBHelper obj_db = new DBHelper(this);

        //Initialising all the text views
        mfname = (EditText)findViewById(R.id.fname_reg);
        mlname = (EditText)findViewById(R.id.lname_reg);
        memail = (EditText)findViewById(R.id.email_reg);
        mpass = (EditText)findViewById(R.id.pass_reg);

        sharedpref = getSharedPreferences(MyPREF, Context.MODE_PRIVATE);

        //Setting an ONClickListener button for registration
        //That enters data into database
        Button reg_button = (Button)findViewById(R.id.reg_button);
        reg_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                String fname = mfname.getText().toString();
                String lname = mlname.getText().toString();
                String email = memail.getText().toString();
                String pass = mpass.getText().toString();
                obj_db.insertContact(fname,lname,email,pass);
                SharedPreferences.Editor editor = sharedpref.edit();
                editor.putString(Email, email);
                editor.putString(Pass, pass);
                editor.commit();
                Toast.makeText(getApplicationContext(),"Registered Successfully, Please Login",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterAct.this,LoginActivity.class);
                startActivity(intent);

            }
        });
    }

}
