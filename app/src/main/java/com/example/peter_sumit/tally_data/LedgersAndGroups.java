package com.example.peter_sumit.tally_data;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by rsinha on 6/29/16.
 */
public class LedgersAndGroups {
    String name,parent;
    boolean isLedger;
    double opBal,clBal,janCr,janDr, febCr, febDr, marCr, marDr, aprCr, aprDr, mayCr, mayDr, junCr, junDr, julCr, julDr, augCr, augDr, sepCr, sepDr, octCr, octDr, novCr, novDr, decCr, decDr;

    public LedgersAndGroups(){

    }

    public LedgersAndGroups(DataInputStream d){
        try{
            name=d.readUTF();
            parent=d.readUTF();
            isLedger=d.readBoolean();
            opBal=d.readDouble();
            clBal=d.readDouble();
            janCr=d.readDouble();
            janDr=d.readDouble();
            febCr=d.readDouble();
            febDr=d.readDouble();
            marCr=d.readDouble();
            marDr=d.readDouble();
            aprCr=d.readDouble();
            aprDr=d.readDouble();
            mayCr=d.readDouble();
            mayDr=d.readDouble();
            junCr=d.readDouble();
            junDr=d.readDouble();
            julCr=d.readDouble();
            julDr=d.readDouble();
            augCr=d.readDouble();
            augDr=d.readDouble();
            sepCr=d.readDouble();
            sepDr=d.readDouble();
            octCr=d.readDouble();
            octDr=d.readDouble();
            novCr=d.readDouble();
            novDr=d.readDouble();
            decCr=d.readDouble();
            decDr=d.readDouble();

        }catch(Exception ex){
            Log.i("Check",ex.getMessage());
        }
        display();
    }
    public void fetchData(JSONObject jo){
        try {
            name = jo.getString("Name"); //COLUMN NAME LABEL
            parent = jo.getString("Parent"); //COLUMN NAME VALUE
            isLedger = jo.getBoolean("IsLedger");
            opBal = jo.getDouble("OpBal");
            clBal = jo.getDouble("ClBal");
            janCr = jo.getDouble("JanCr");//Jan
            janDr = jo.getDouble("JanDr");
            febCr = jo.getDouble("FebCr");//Feb
            febDr = jo.getDouble("FebDr");
            marCr = jo.getDouble("MarCr");//Mar
            marDr = jo.getDouble("MarDr");
            aprCr = jo.getDouble("AprCr");//Apr
            aprDr = jo.getDouble("AprDr");
            mayCr = jo.getDouble("MayCr");//May
            mayDr = jo.getDouble("MayDr");
            junCr = jo.getDouble("JunCr");//jun
            junDr = jo.getDouble("JunDr");
            julCr = jo.getDouble("JulCr");//july
            julDr = jo.getDouble("JulDr");
            augCr = jo.getDouble("AugCr");//Aug
            augDr = jo.getDouble("AugDr");
            sepCr = jo.getDouble("SepCr");//SEP
            sepDr = jo.getDouble("SepDr");
            octCr = jo.getDouble("OctCr");//Oct
            octDr = jo.getDouble("OctDr");
            novCr = jo.getDouble("NovCr");//Nov
            novDr = jo.getDouble("NovDr");
            decCr = jo.getDouble("DecCr");//Dec
            decDr = jo.getDouble("DecDr");
            display();
        }catch(Exception ex){
            Log.i("Check",ex.getMessage());
        }
    }
    public void display(){
        Log.i("Check",name+" "+parent+" "+isLedger+" "+opBal+" "+clBal);
    }
    public void printData(DataOutputStream d){
        try{
            d.writeUTF(name);
            d.writeUTF(parent);
            d.writeBoolean(isLedger);
            d.writeDouble(opBal);
            d.writeDouble(clBal);
            d.writeDouble(janCr);
            d.writeDouble(janDr);
            d.writeDouble(febCr);
            d.writeDouble(febDr);
            d.writeDouble(marCr);
            d.writeDouble(marDr);
            d.writeDouble(aprCr);
            d.writeDouble(aprDr);
            d.writeDouble(mayCr);
            d.writeDouble(mayDr);
            d.writeDouble(junCr);
            d.writeDouble(junDr);
            d.writeDouble(julCr);
            d.writeDouble(julDr);
            d.writeDouble(augCr);
            d.writeDouble(augDr);
            d.writeDouble(sepCr);
            d.writeDouble(sepDr);
            d.writeDouble(octCr);
            d.writeDouble(octDr);
            d.writeDouble(novCr);
            d.writeDouble(novDr);
            d.writeDouble(decCr);
            d.writeDouble(decDr);
        }catch(Exception ex){
            Log.i("Check",ex.getMessage());
        }

    }


}
