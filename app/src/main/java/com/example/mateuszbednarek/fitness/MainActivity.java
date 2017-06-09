package com.example.mateuszbednarek.fitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Defining views
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextAge;
    private EditText editTextGender;
    private EditText editTextType;
    private EditText editTextEmail;
    private EditText editTextDate;
    private EditText editTextPass;


    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSurname = (EditText) findViewById(R.id.editTextSurname);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextGender = (EditText) findViewById(R.id.editTextGender);
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
       // buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        //buttonView.setOnClickListener(this);
    }
    //Adding an employee
    private void addClient(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE,0);
        today.set(Calendar.SECOND,0);
        final String name = editTextName.getText().toString().trim();
        final String surname = editTextSurname.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();
        final String gender = editTextGender.getText().toString().trim();
        final String type = editTextType.getText().toString().trim();
        final String date = today.toString();
        final String email = editTextEmail.getText().toString().trim();
        final String pass = editTextPass.getText().toString().trim();

        class addClient extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Dodaje...","Prosze czekać...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,"Pomyslnie dodano klienta.",Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMP_NAME,name);
                params.put(Config.KEY_EMP_SURNAME,surname);
                params.put(Config.KEY_EMP_AGE,age);
                params.put(Config.KEY_EMP_GENDER,gender);
                params.put(Config.KEY_EMP_TYPE,type);
                params.put(Config.KEY_EMP_EXPIRE,date);
                params.put(Config.KEY_EMP_EMAIL,email);
                params.put(Config.KEY_EMP_PASS,pass);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }

        addClient ac = new addClient();
        ac.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addClient();
        }
    }
}
