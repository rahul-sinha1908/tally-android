package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class sucess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);
    }

    public void income(View v){
        startActivity(new Intent(this, Income.class));
    }
    public void pnl(View v){
        startActivity(new Intent(this, PNL.class));
    }

}
