package com.ankita.carfaxtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ankita.carfaxtask.adapter.AdapterMainList;
import com.ankita.carfaxtask.model.MainScreen;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<MainScreen> listData = new ArrayList<>();
    // URL to get contacts JSON
    private static String apiurl = "https://carfax-for-consumers.firebaseio.com/assignment.json";
    private String TAG = MainActivity.class.getName();
    private ProgressDialog progressDialog;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.maianrecyler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setNestedScrollingEnabled(false);
       adapter=new AdapterMainList(listData,getApplicationContext());
        recyclerView.setAdapter(adapter);
        getData();

    }

    private void getData(){
        StringRequest request = new StringRequest(Request.Method.GET, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Log.d("data",response);
                if(!response.isEmpty()){
                try {


                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("listings");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        String model = jsonObject1.getString("model");
                        String yearr = jsonObject1.getString("year");
                        String trim = jsonObject1.getString("trim");
                        String id = jsonObject1.getString("trim");
                        String make = jsonObject1.getString("make");
                        String listPrice = jsonObject1.getString("listPrice");
                        String mileage = jsonObject1.getString("mileage");
                        String dealer = jsonObject1.getString("dealer");
                        JSONObject obj = new JSONObject(dealer);
                        String address = obj.getString("address");



                        String ii = jsonObject1.getString("images");
                        JSONObject obj1 = new JSONObject(ii);
                        JSONObject firstPhotos = obj1.getJSONObject("firstPhoto");
                        String image = firstPhotos.getString("medium");

                      //  String durl = URLDecoder.decode(pic,"UTF-8");

                        //String image = pic.replace("\\","");

//                        String finalurl = new Scanner(new URL(pic).openStream(),"utf-88").useDelimiter("\\A").next();




                        MainScreen mainScreen = new MainScreen();
                        mainScreen.setLocation(address);
                        mainScreen.setMake(make);
                        mainScreen.setModel(model);
                        mainScreen.setYear(yearr);
                        mainScreen.setTrim(trim);
                        mainScreen.setMilage(mileage);
                        mainScreen.setPrice(listPrice);
                        mainScreen.setvPhoto(image);

                        listData.add(mainScreen);

                        Log.d("response : ",image);
//                        Log.d("response : ",year);
//                        Log.d("response : ",address);
                    }

                    adapter.notifyDataSetChanged();


//                    SharedPreferences sharedPreferences = getSharedPreferences("giga",MODE_PRIVATE);
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putString("userId",userId);
//                    editor.putString("name",name);
//                    editor.putString("password",password);
//                    editor.putString("clubId",clubId);
//                    editor.commit();
//                    SharedPrefrenceUtils.getInstance().saveString("userId",userId);
//                    SharedPrefrenceUtils.getInstance().saveString("name",name);
//                    SharedPrefrenceUtils.getInstance().saveString("password",password);
//                    SharedPrefrenceUtils.getInstance().saveString("clubId",clubId);

                    //   Log.d("response", response);
                    String checking = jsonObject.toString();
                    Log.d("check", response);
                    Toast.makeText(getApplicationContext(), "working", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this,HomePage.class);
//                    startActivity(intent);
//                    finish();

                } catch (JSONException e) {

                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;

                if (response != null && response.statusCode == 404) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        //use this json as you want
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
                // Hiding the progress dialog after all task complete.
                // progressDialog.dismiss();
                // Showing error message if something goes wrong.
//   Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();


            }
        }
        );

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }


    }

