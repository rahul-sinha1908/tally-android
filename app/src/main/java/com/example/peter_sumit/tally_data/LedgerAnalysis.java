package com.example.peter_sumit.tally_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LedgerAnalysis extends AppCompatActivity {
    static int ind;
    LedgersAndGroups[] dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger_analysis);

        dat = MainActivity.data;

        Log.i("Check",dat[ind].name);
        TextView head = (TextView)findViewById(R.id.ledger_head);
        head.setText("Ledger : " +dat[ind].name);

    }
}
