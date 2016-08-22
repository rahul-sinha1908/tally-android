package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Income extends AppCompatActivity {

    private static String groups[]={
            "Direct Incomes",
            "Indirect Incomes",
            "Sales Accounts"
            //"Branch / Divisions",
            //"Capital Account",
            //"Reserves & Surplus",
            //"Current Assets",
            //"Bank Accounts",
            //"Cash-in-hand",
            //"Cash",
            //"Deposits (Asset)",
            //"Loans & Advances (Asset)",
            //"Stock-in-hand",
            //"Sundry Debtors",
            //"Current Liabilities",
            //"Duties & Taxes",
            //"Provisions",
            //"Sundry Creditors",
            //"Fixed Assets",
            //"Investments",
            //"Loans (Liability)",
            //"Bank OD A/c",
            //"Secured Loans",
            //"Unsecured Loans",
            //"Misc. Expenses (ASSET)",
            //"Suspense A/c",
            //"Profit & Loss A/c"
    };

    LinearLayout cont;
    LinearLayout hor[];
    int len;
    LedgersAndGroups[] dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        Log.i("Check", "Its at Inc 1" );
        cont = (LinearLayout)findViewById(R.id.income_cont);
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
