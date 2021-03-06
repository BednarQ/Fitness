package com.example.mateuszbednarek.fitness;

/**
 * Created by Mateusz Bednarek on 03.06.2017.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
public class ViewAllClients extends AppCompatActivity implements ListView.OnItemClickListener {
    private ListView listView;

    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_clients);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        Log.d("STRING",JSON_STRING);
        try {

            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String name = jo.getString(Config.TAG_NAME)+" "+jo.getString(Config.TAG_SURNAME);
                String type = "Typ karnetu: "+jo.getString(Config.TAG_TYPE) + "\nData ważności karnetu: "+
                        jo.getString(Config.TAG_EXPIRE);

                HashMap<String,String> client = new HashMap<>();
                client.put(Config.TAG_ID, id);
                client.put(Config.TAG_NAME,name);
                client.put(Config.TAG_SURNAME,type);
                list.add(client);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewAllClients.this, list, R.layout.list_item,
                new String[]{Config.TAG_NAME,Config.TAG_SURNAME, Config.TAG_TYPE, Config.TAG_EXPIRE},
                new int[]{R.id.id, R.id.name});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewAllClients.this,"Pobieram dane","Proszę czekać...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewSelectedClient.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);

        String empId = map.get(Config.TAG_ID).toString();
        Log.d("ID", empId);
        intent.putExtra(Config.CLIENT_ID,empId);
        startActivity(intent);
    }

}
