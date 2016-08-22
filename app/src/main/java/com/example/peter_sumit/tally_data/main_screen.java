package com.example.peter_sumit.tally_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class main_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }//End of oncreate
    public void Income(View view){
        startActivity(new Intent(this, Income.class));
    }

    public void Expenditure(View view){
        startActivity(new Intent(this, Expenditure.class));
    }
    public void Cash_and_bank(View view)
    {
        startActivity(new Intent(this, cashandbank.class));
    }
    public void Stock(View view){
        startActivity(new Intent(this, stock.class));
    }
    public void Loan(View view){
        startActivity(new Intent(this, loan.class));
    }
    public void Receivable(View view){
        startActivity(new Intent(this, Recievable.class));
    }
    public void Payable(View view){
        startActivity(new Intent(this, Payable.class));
    }


}//End of Main_screen class
