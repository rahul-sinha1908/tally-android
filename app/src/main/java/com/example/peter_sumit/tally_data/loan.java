package com.example.peter_sumit.tally_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class loan extends AppCompatActivity {

    private static String groups[]={
            //"Direct Incomes",
            //"Indirect Incomes",
            //"Sales Accounts"
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
            "Bank OD A/c",
            "Secured Loans",
            "Unsecured Loans",
            //"Misc. Expenses (ASSET)",
            //"Suspense A/c",
            //"Profit & Loss A/c"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
    }
}
