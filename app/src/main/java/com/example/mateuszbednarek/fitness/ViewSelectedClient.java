package com.example.mateuszbednarek.fitness;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewSelectedClient extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextAge;
    private EditText editTextGender;
    private EditText editTextType;
    private EditText editTextEmail;
    private EditText editTextDate;
    private EditText editTextPass;

    private Button buttonUpdate;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_selected_client);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.CLIENT_ID);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSurname = (EditText) findViewById(R.id.editTextSurname);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextGender = (EditText) findViewById(R.id.editTextGender);
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(this);

        Log.d("Client ID", id);
        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewSelectedClient.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_SEL,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_NAME);
            String surname = c.getString(Config.TAG_SURNAME);
            String gender = c.getString(Config.TAG_GENDER);
            String age = c.getString(Config.TAG_AGE);
            String type = c.getString(Config.TAG_TYPE);
            String expire = c.getString(Config.TAG_EXPIRE);
            String email = c.getString(Config.TAG_EMAIL);
            String pass = c.getString(Config.TAG_PASS);

            editTextName.setText(name);
            editTextSurname.setText(surname);
            editTextAge.setText(age);
            editTextGender.setText(gender);
            editTextType.setText(type);
            editTextDate.setText(expire);
            editTextEmail.setText(email);
            editTextPass.setText(pass);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee() {
        final String name = editTextName.getText().toString().trim();
        final String surname = editTextSurname.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();
        final String gender = editTextGender.getText().toString().trim();
        final String type = editTextType.getText().toString().trim();
        final String date = editTextDate.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String pass = editTextPass.getText().toString().trim();


        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewSelectedClient.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewSelectedClient.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_EMP_ID, id);
                hashMap.put(Config.KEY_EMP_NAME, name);
                hashMap.put(Config.KEY_EMP_SURNAME, surname );
                hashMap.put(Config.KEY_EMP_AGE, age);
                hashMap.put(Config.KEY_EMP_GENDER, gender);
                hashMap.put(Config.KEY_EMP_TYPE, type);
                hashMap.put(Config.KEY_EMP_EXPIRE, date);
                hashMap.put(Config.KEY_EMP_EMAIL, email);
                hashMap.put(Config.KEY_EMP_PASS, pass);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_SEL, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }
    }
}
