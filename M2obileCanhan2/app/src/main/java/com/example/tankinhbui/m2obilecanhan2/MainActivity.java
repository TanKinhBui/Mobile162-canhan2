package com.example.tankinhbui.m2obilecanhan2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends Activity {
    ListView lv, lv1, lv2;
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    FragmentTransaction ft;
    PM_Fragment redFragment;
    LM_Fragment blueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listprovince);
        lv1 = (ListView) findViewById(R.id.listviewmain);
        lv2 = (ListView) findViewById(R.id.listviewdate);

        Configuration config = getResources().getConfiguration();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://thanhhungqb.tk:8080/kqxsmn");
            }
        });


        // create a new BLUE fragment - show it
       // ft = getFragmentManager().beginTransaction();
        //blueFragment = LM_Fragment.newInstance("first-blue");
        //ft.replace(android.R.id.content, blueFragment);
        //ft.commit();
// create a new RED fragment - show it


    }
    // MainCallback implementation (receiving messages coming from Fragments)

    public void onMsgFromFragToMain(String sender, String strValue) {
// show message arriving to MainActivity
        Toast.makeText(getApplication(),
                " MAIN GOT>> " + sender + "\n" + strValue, Toast.LENGTH_LONG)
                .show();
        if (sender.equals("RED-FRAG")) {
// TODO: if needed, do here something on behalf of the RED fragment
        }
        if (sender.equals("BLUE-FRAG")) {
            try {
// forward blue-data to redFragment using its callback method
                Toast.makeText(getApplication(),
                        " MAIN GOT>> " + sender + "\n" + strValue, Toast.LENGTH_LONG)
                        .show();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
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
                final ArrayList<Area> area = new ArrayList<Area>();
                ArrayList<String> listProvince = new ArrayList<String>();
                for (int i=0; i<arrayProvince.length();i++) {
                    String tmp = arrayProvince.getString(i);
                    listProvince.add(tmp);
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
                    for (int t = 0; t < area.size(); t++) {
                        Log.e("++++++++++++++", area.get(t).getArea());
                        for (int a=0;a<area.get(t).getDate().size();a++) {
                            Log.e("--------------", area.get(t).getDate().get(a).getDate());
                        }
                    }

                    //Toast.makeText(MainActivity.this, kkk.toString(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this, arrayDate.toString(), Toast.LENGTH_LONG).show();

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        listProvince
                );
                lv1.setAdapter(adapter);
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Area temp = area.get(position);
                        ArrayList<DateA> tmpdate1 = temp.getDate();
                        for (int p = 0; p<tmpdate1.size();p++){
                            Log.e("------asd", tmpdate1.get(p).getDate());
                            Log.e("------asd", tmpdate1.get(p).getGiai1());
                        }
                        ListAdapter adapter1 = new ListAdapter(
                                MainActivity.this,
                                R.layout.item,
                                tmpdate1
                        );
                        lv2.setAdapter(adapter1);

                    }
                });


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