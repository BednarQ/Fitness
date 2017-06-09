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
public class GetClasses extends AppCompatActivity {
    private ListView listView;

    private String JSON_STRING;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_classes);
        listView = (ListView) findViewById(R.id.Classes);
        getJSON();
    }

    private void showClasses(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        Log.d("STRING",JSON_STRING);
        try {

            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String name = "Zajecia: "+jo.getString(Config.TAG_CNAME) + "\nDla kogo: "+jo.getString(Config.TAG_FOR);
                String hour = "Godzina rozpoczęcia "+jo.getString(Config.TAG_START) + "\nGodzina zakończenia:  "+
                        jo.getString(Config.TAG_END)+"\nProwadsszący: "+jo.getString(Config.TAG_TNAME)+" "+jo.getString(Config.TAG_TSURNAME);
                String trener = "Prowadzący: "+jo.getString(Config.TAG_TNAME)+" "+jo.getString(Config.TAG_TSURNAME);

                HashMap<String,String> classes = new HashMap<>();
                classes.put(Config.TAG_CNAME,name);
                classes.put(Config.TAG_START,hour);
                classes.put(Config.TAG_TNAME,trener);
                list.add(classes);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                GetClasses.this, list, R.layout.list_item_class,
                new String[]{Config.TAG_CNAME,Config.TAG_FOR, Config.TAG_START, Config.TAG_END,Config.TAG_TNAME,Config.TAG_TSURNAME},
                new int[]{R.id.className, R.id.hour, R.id.trener});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(GetClasses.this,"Pobieram dane","Proszę czekać...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showClasses();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_CLASSES);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
