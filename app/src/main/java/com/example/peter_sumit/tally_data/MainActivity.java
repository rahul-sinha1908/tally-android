package com.example.peter_sumit.tally_data;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String domain="http://tallyproject1.hol.es/";
    String user,pass;
    static String compName="ActNow";
    static LedgersAndGroups data[];
    static String lastDate,lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void btnPress(View v){
        user = ((EditText)findViewById(R.id.userName)).getText().toString();
        pass = ((EditText)findViewById(R.id.passWord)).getText().toString();

        Log.i("Check", "Its Here 1");
        //new Login(this).execute();
        //new GetData(this).execute();
        readData();
    }


//*****************************************************************************


    public class Login extends AsyncTask<String, String, String> {
        Context cont;
        private ProgressDialog dialog;

        public Login(MainActivity m) {
            cont = m;
            dialog = new ProgressDialog(m);
            dialog.setCancelable(false);
        }
        @Override
        protected void onPreExecute() {
            dialog.setMessage("Connecting, please wait.");
            dialog.show();
        }
        boolean b=false;
        @Override
        protected String doInBackground(String... params) {
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(domain+"php/userValid.php");
                List<NameValuePair> ln=new ArrayList<NameValuePair>();
                ln.add(new BasicNameValuePair("user",user));
                httpPost.setEntity(new UrlEncodedFormEntity(ln));
                HttpResponse response=httpClient.execute(httpPost);
                publishProgress("Reading Data...");
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line=br.readLine();
                if(line.equals(pass)){
                    b=true;
                }
                else
                {
                    Log.i("Check",br.readLine());
                }
            }
            catch (Exception e) {
                Log.i("Check","Other Exception = "+e.getMessage());
            }

            return "YOLO";
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            if(b) {
                Toast.makeText(getApplicationContext(), "Authorisation Succesful", Toast.LENGTH_LONG).show();
                Log.i("Check", "Done");
                new GetData(cont).execute();
            }
            else {
                Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_LONG).show();
                Log.i("Check","Incorrect");
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            dialog.setMessage(values[0]);
        }
    }


//*****************************************************************************

    void readData(){
        boolean b=false;
        try {
            DataInputStream din = new DataInputStream(openFileInput(MainActivity.compName + ".txt"));
            b=true;
            int len=din.readInt();
            data = new LedgersAndGroups[len];
            for(int i=0;i<len;i++){
                data[i]=new LedgersAndGroups(din);
            }
            din.close();
            startActivity(new Intent(this, main_screen.class));
            finish();
        }catch(Exception ex){
            Log.i("Check",ex.getMessage());
            if(b==false){
                new GetData(this).execute();
            }
        }
    }


//*****************************************************************************


    class GetData extends AsyncTask<String, String, String> {
        Context cont;
        public String domain="http://tallyproject1.hol.es/";
        private ProgressDialog dialog;
        String error;

        public GetData(Context m){
            cont =m;
            dialog=new ProgressDialog(m);
            dialog.setCancelable(false);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Connecting, please wait.");
            dialog.show();
        }
        boolean b=false;
        String table[]={"LedgersAndGroups"};
        @Override
        protected String doInBackground(String... params) {         //DOWNLOADING DATA FROM HOSTINGER
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(domain+"php/download.php");    //public html->php
                List<NameValuePair> ln=new ArrayList<NameValuePair>();
                ln.add(new BasicNameValuePair("table",compName));
                httpPost.setEntity(new UrlEncodedFormEntity(ln));
                HttpResponse response=httpClient.execute(httpPost);

                publishProgress("Creating Entity...");
                HttpEntity ent = response.getEntity();

                publishProgress("Reading Entity...");
                String para = EntityUtils.toString(ent);
                //Log.i("Check", para);
                publishProgress("Parsing Data...");

                JSONArray ja=new JSONArray(para);
                Log.i("Check","Its at 2");
                int len = ja.length();
                data =new LedgersAndGroups[len];
                Log.i("Check","Its at 3  "+len);
                for(int i=0;i<len;i++){
                    data[i]=new LedgersAndGroups();
                    data[i].fetchData(ja.getJSONObject(i));
                }
                Log.i("Check","Its at 4");
                DataOutputStream dos = new DataOutputStream(openFileOutput(MainActivity.compName+".txt",0));
                dos.writeInt(len);
                for(int i=0;i<len;i++){
                    data[i].printData(dos);
                }
                dos.close();
                Log.i("Check","Its at 5");
                b=true;
            }
            catch (Exception e) {
                Log.i("Check","Other Exception = "+e.getMessage());
                error = e.getMessage();
            }
            return "Hello";
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            if(b) {
                startActivity(new Intent(cont, main_screen.class));
                finish();
            }else{
                Toast.makeText(cont, error, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.i("Check",values[0]);
            dialog.setMessage(values[0]);
        }
    }


}