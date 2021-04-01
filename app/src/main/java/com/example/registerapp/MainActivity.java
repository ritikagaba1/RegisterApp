package com.example.registerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RegisterListAdapter adapter;
    ListView BatchList;
    private InputStream is = null;
    private String result = null;
    private String line = null;
    ArrayList<RegisterListAd> batchData = new ArrayList<RegisterListAd>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        BatchList = findViewById(R.id.batch_list);
        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();

        {
            if (nf != null && nf.isConnected() == true) {
                //   Toast.makeText(this, "Internet Connection Available", Toast.LENGTH_LONG).show();

                new loadBmrList1().execute();

            } else {
                Toast.makeText(this, getResources().getString(R.string.connection_error_message), Toast.LENGTH_LONG).show();

            }

        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterList.class);
                //	            intent.putExtras(dataBundle);
//                startActivity(intent);
                startActivityForResult(intent, 2);
//                Toast.makeText(getApplicationContext(),"jjj",Toast.LENGTH_LONG).show();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    public class loadBmrList1 extends AsyncTask<ArrayList<String>, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        protected String doInBackground(ArrayList<String>... alldata) {

//            ArrayList<String> passed = alldata[0]; //get passed arraylist
//            String batch_name = passed.get(0);
//            String batch_location = passed.get(1);
//            String batch_teacher = passed.get(2);
//            String batchtime = passed.get(3);
//            String batch_status = passed.get(4);
//            String maximumslot = passed.get(5);
//            // current time

            try {
                // Log.e(" setup Activity ", "  user detail to server " + " "+ SendName+" "+Sendmobile+" "+Checkgender);
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

//                nameValuePairs.add(new BasicNameValuePair("batch_name", batch_name ));
//                nameValuePairs.add(new BasicNameValuePair("batch_location", batch_location));
//                nameValuePairs.add(new BasicNameValuePair("batch_teacher",  batch_teacher));
//                nameValuePairs.add(new BasicNameValuePair("batchtime", batchtime));
//                nameValuePairs.add(new BasicNameValuePair("batch_status", batch_status));
//                nameValuePairs.add(new BasicNameValuePair("user_id", userId));
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://edumanagerbook.com/whatspromo_app.php?action=display_register_one");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")); // UTF-8  support multi language
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                Log.e("pass 1", "connection success ");
            } catch (Exception e) {
                Log.e("Fail 1", e.toString());
                //  Log.d("setup Activity ","  fail 1  "+e.toString());
            }

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
                Log.d("pass 2", "connection success " + result);
            } catch (Exception e) {
                Log.e("Fail 2", e.toString());
                //  Log.e("setup Activity  "," fail  2 "+ e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(" setup acc ", "  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            JSONArray jArray = null;
            try {
                jArray = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {

//                JSONObject jObj = new JSONObject(result);
                String Customer_name = "";
                String whatsapp_number = "";
                String email_id = "";
                String plan_start_date = "";
                String plan_end_date = "";
                String plan_amount = "";
                Toast.makeText(getApplicationContext(), jArray.length() + "jjj", Toast.LENGTH_LONG).show();
                for (int i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        Customer_name = jsonObject.getString("Customer_name");
                        whatsapp_number = jsonObject.getString("whatsapp_number");
                        email_id = jsonObject.getString("email_id");
                        plan_start_date = jsonObject.getString("plan_start_date");
                        plan_end_date = jsonObject.getString("plan_end_date");
                        plan_amount = jsonObject.getString("plan_amount");
//                        batchIdArray.add(jsonObject.getString("auto_id"));
                        batchData.add(new RegisterListAd(Customer_name, whatsapp_number, email_id, plan_start_date + "-" + plan_end_date, plan_amount));
//                        Toast.makeText(getApplicationContext(), "batch_name" + student_name, Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
//                    batchData.add(new OwnerListAd(batch_name, batch_status));
                adapter = new RegisterListAdapter(MainActivity.this, batchData);
                BatchList.setAdapter(adapter);

                BatchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
//                     builderSingle.setIcon(R.drawable.ic_start_up);
                        builderSingle.setTitle("Select Option");

                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
                        arrayAdapter.add("Edit");
                        arrayAdapter.add("Delete");

                        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });


//                     adapter.notifyDataSetChanged();
//                     String batch_id= (String) batchIdArray.get(position);
//                     Intent intent=new Intent(BatchList.this,EditBatchFields.class);
//                     intent.putExtra("batch_id",batch_id);
//                     startActivity(intent);

                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
