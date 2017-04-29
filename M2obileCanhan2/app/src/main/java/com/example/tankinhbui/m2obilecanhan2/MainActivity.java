package com.example.tankinhbui.m2obilecanhan2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends Activity {
    ListView lv, lv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lv = (ListView) findViewById(R.id.listprovince);
        lv1 = (ListView) findViewById(R.id.listview1);

        Configuration config = getResources().getConfiguration();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://thanhhungqb.tk:8080/kqxsmn");
            }
        });

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();



        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            LM_Fragment lm_Fragment = new LM_Fragment();
            fragmentTransaction.replace(android.R.id.content, lm_Fragment);
        }else{
            PM_Fragment pm_Fragment = new PM_Fragment();
            fragmentTransaction.replace(android.R.id.content, pm_Fragment);
        }

        fragmentTransaction.commit();
    }

    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String kq = docNoiDung_Tu_URL(params[0]);
            return kq;
        }
        protected void onPostExecute(final String s){
            //Toast.makeText(ConvertActivity.this, "-------------------", Toast.LENGTH_LONG).show();
            //Toast.makeText(MainActivity.this, "sssssssssssss", Toast.LENGTH_LONG).show();
            try {
                JSONObject root = new JSONObject(s);
                JSONArray arrayProvince = root.names();
                //Toast.makeText(MainActivity.this, arrayProvince.toString(), Toast.LENGTH_LONG).show();
                ArrayList<Area> area = new ArrayList<Area>();
                for (int i=0; i<arrayProvince.length();i++) {
                    String tmp = arrayProvince.getString(i);
                    ArrayList<DateA> kqDate = new ArrayList<DateA>();
                    JSONObject province = root.getJSONObject(tmp);
                    JSONArray arrayDate = province.names();

                    for (int j = 0; j < arrayDate.length(); j++) {
                        String tmpDate = arrayDate.getString(j);
                        JSONObject kq = province.getJSONObject(tmpDate);

                        JSONArray tgiai1 = kq.getJSONArray("1");
                        JSONArray tgiai2 = kq.getJSONArray("2");
                        JSONArray tgiai3 = kq.getJSONArray("3");
                        JSONArray tgiai4 = kq.getJSONArray("4");
                        JSONArray tgiai5 = kq.getJSONArray("5");
                        JSONArray tgiai6 = kq.getJSONArray("6");
                        JSONArray tgiai7 = kq.getJSONArray("7");
                        JSONArray tgiai8 = kq.getJSONArray("8");
                        JSONArray tDB = kq.getJSONArray("DB");
                        String giai1 = tgiai1.toString();
                        String giai2 = tgiai2.toString();

                        String giai3 = tgiai3.toString();
                        String giai4 = tgiai4.toString();
                        String giai5 = tgiai5.toString();
                        String giai6 = tgiai6.toString();
                        String giai7 = tgiai7.toString();
                        String giai8 = tgiai8.toString();
                        String DB = tDB.toString();

                        DateA kk = new DateA();

                        kk.setDate(tmpDate);
                        kk.setGiai1(giai1);
                        kk.setGiai2(giai2);
                        kk.setGiai3(giai3);
                        kk.setGiai4(giai4);
                        kk.setGiai5(giai5);
                        kk.setGiai6(giai6);
                        kk.setGiai7(giai7);
                        kk.setGiai8(giai8);
                        kk.setDB(DB);

                        //Toast.makeText(MainActivity.this, tmpDate, Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, giai3.toString(), Toast.LENGTH_LONG).show();
                        kqDate.add(kk);
                    }
                    Area kkk = new Area();
                    kkk.setArea(tmp);
                    kkk.setDate(kqDate);
                    area.add(kkk);
                    for (int t = 0; t < kqDate.size(); t++)
                        Log.e("++++++++++++++", kqDate.get(t).getDate());
                    /*
                    ArrayAdapter adapter = new ArrayAdapter(
                            MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            kqDate
                    );
                    lv1.setAdapter(adapter);
                    */

                    //Toast.makeText(MainActivity.this, kkk.toString(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, arrayDate.toString(), Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

}