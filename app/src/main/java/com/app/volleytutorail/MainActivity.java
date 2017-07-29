package com.app.volleytutorail;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    ProgressDialog loading;
    ListView listView;
    ArrayList<POJO> pojos = new ArrayList<>();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView);
        button = (Button) findViewById(R.id.signUp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();

            }
        });

         Login();
    }

    public void Login()
    {
        String Url = "http://epay.cybussolutions.com/Api_Service/getAllUsers";

          loading = ProgressDialog.show(MainActivity.this, "Please wait...", "Getting Data From Server ...", false, false);

        StringRequest request = new StringRequest(Request.Method.POST,Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    loading.dismiss();
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i =0 ; i<jsonArray.length();i++)
                    {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        POJO pojo = new POJO();

                        pojo.setFirstName(jsonObject.getString("fname"));
                        pojo.setLastName(jsonObject.getString("lname"));
                        pojo.setId(jsonObject.getString("customer_id"));
                        pojo.setProfile(jsonObject.getString("profile_pic"));

                        pojos.add(pojo);


                    }

                    ListAddapter listAddapter = new ListAddapter(MainActivity.this,R.layout.list_row,pojos);
                   listView.setAdapter(listAddapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }
                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                   loading.dismiss();
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }


        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }


    public void signUp()
    {
        String Url = "http://epay.cybussolutions.com/Api_Service/signupUser";

        loading = ProgressDialog.show(MainActivity.this, "Please wait...", "Getting Data From Server ...", false, false);

        StringRequest request = new StringRequest(Request.Method.POST,Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                loading.dismiss();

                Toast.makeText(MainActivity.this,response, Toast.LENGTH_SHORT).show();

            }

        }
                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }


        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("email","hamzabintariq123@yahoo.com");
                map.put("first_name","hamza");
                map.put("last_name","tariq");
                map.put("password","123456789");
                map.put("phone_number","123456789");
                map.put("address","house 1 street 2");
                map.put("gender","1");
                map.put("cardtype","1");

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }


}