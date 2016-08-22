package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PNL extends AppCompatActivity {

    public static String groups[]={"Stock-in-hand",
            "Direct Expenses",
            "Direct Incomes",
            "Indirect Expenses",
            "Indirect Incomes",
            "Purchase Accounts",
            "Sales Accounts"
    };

    LinearLayout cont;
    int len;
    LedgersAndGroups[] dat;
    LinearLayout[] hor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnl);

        Log.i("Check", "Its at Inc 1" );
        cont = (LinearLayout)findViewById(R.id.pnl_cont);
        len=groups.length;
        Log.i("Check", "Its at Inc 2" );
        dat = MainActivity.data;
        Log.i("Check", "Its at Inc 3" );

        hor=new LinearLayout[len+10];
        Log.i("Check", "Its at Inc 4" );

        for(int i=0;i<len+10;i++){
            hor[i] = new LinearLayout(this);
            hor[i].setOrientation(LinearLayout.HORIZONTAL);
            //cont.addView(hor[i]);
        }
        Log.i("Check", "Its at Inc 5  "+dat.length );

        for(int i=0;i<dat.length;i++){
            for(int j=0;j<len;j++){
                if(dat[i].name.equals(groups[j]) && !(dat[i].opBal==0f && dat[i].clBal==0f)){
                    try {
                        Log.i("Check", "Its at Inc 6 i=" + i + "  group =" + groups[j]);
                        cont.addView(hor[j]);
                        Log.i("Check", "Its at Inc 7 i=" + i);
                        TextView txt = new TextView(this);
                        txt.setText(dat[i].name + " " + dat[i].opBal + " " + dat[i].clBal);
                        hor[j].addView(txt);
                        Log.i("Check", "Its at Inc 8 i=" + i);
                        hor[j].setTag(dat[i].name);
                        Log.i("Check", "Its at Inc 9 i=" + i);
                        hor[j].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DisplayLedgerGroups.groupName = v.getTag().toString();
                                drillDown();
                            }
                        });
                        Log.i("Check", "Its at Inc 10 i=" + i);
                    }catch (Exception ex){
                        Log.i("Check","Group = "+groups[j]+"   "+ex.getMessage());
                    }
                }
            }
        }
    }

    void drillDown(){
        startActivity(new Intent(this, DisplayLedgerGroups.class));
    }
}
