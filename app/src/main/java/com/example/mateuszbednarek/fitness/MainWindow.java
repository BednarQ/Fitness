package com.example.mateuszbednarek.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Mateusz Bednarek on 05.06.2017.
 */

public class MainWindow extends AppCompatActivity implements View.OnClickListener {

    Button buttonAdd;
    Button buttonView;
    Button buttonViewClasses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);
        buttonAdd = (Button) findViewById(R.id.addClient);
        buttonView = (Button) findViewById(R.id.viewAll);
        buttonViewClasses = (Button) findViewById(R.id.viewClasses);
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        buttonViewClasses.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            startActivity(new Intent(this,MainActivity.class));
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllClients.class));
        }
        if(v == buttonViewClasses){
            startActivity(new Intent(this,GetClasses.class));
        }
    }
}

