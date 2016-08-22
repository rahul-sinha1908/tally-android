package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayLedgerGroups extends AppCompatActivity {

    public static String groupName;
    LinearLayout cont;
    LinearLayout hor[];
    int len;
    LedgersAndGroups[] dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ledger_groups);

        cont = (LinearLayout)findViewById(R.id.lng_cont);
        dat = MainActivity.data;
        len=dat.length;

        TextView head = (TextView)findViewById(R.id.group_Head);
        head.setText(groupName);

        hor=new LinearLayout[len];

        for(int i=0;i<len;i++){
            hor[i] = new LinearLayout(this);
            hor[i].setOrientation(LinearLayout.HORIZONTAL);
            //cont.addView(hor[i]);
        }

        for(int i=0;i<dat.length;i++){
                if(dat[i].parent.equals(groupName) && !(dat[i].opBal==0f && dat[i].clBal==0f)){
                    cont.addView(hor[i]);
                    TextView txt=new TextView(this);
                    txt.setText(dat[i].name+" "+dat[i].opBal+" "+dat[i].clBal);
                    hor[i].addView(txt);

                    if(dat[i].isLedger) {
                        hor[i].setTag(dat[i].name);
                        hor[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DisplayLedgerGroups.groupName=v.getTag().toString();
                                drillDown();
                            }
                        });
                    }else{
                        hor[i].setTag(""+i);
                        hor[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LedgerAnalysis.ind =Integer.parseInt(v.getTag().toString());
                                openLedger();
                            }
                        });
                    }
                }
        }

    }
    void drillDown(){
        startActivity(new Intent(this, DisplayLedgerGroups.class));
    }
    void openLedger(){
        startActivity(new Intent(this, LedgerAnalysis.class));
    }

}
