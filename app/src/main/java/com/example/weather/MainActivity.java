package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText edCity;
    TextView result;
    //    https://api.openweathermap.org/data/2.5/weather?q=Lucknow&appid=454ad9127c82323a06008c78840288b6
    String baseUrl = "https://api.openweathermap.org/data/2.5/weather?q=";
    String API = "&appid=454ad9127c82323a06008c78840288b6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.getWeather);
        edCity = findViewById(R.id.editText);
        result = findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edCity.getText().toString();
                if (edCity.length() == 0) {
                    edCity.setError("Please Enter City Name");
                } else {
                    String myUrl = baseUrl + city + API;
//                Log.i("url","url:" + myUrl);
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//                    RequestQueue  queue = Singleton.getInstance(this).getmRequestQueue();
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, myUrl, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONObject object = response.getJSONObject("main");
                                String temperature = object.getString("temp");
                                Double temp = Double.parseDouble(temperature) - 273.15;
                                result.setText("Temperature : " + temp.toString().substring(0, 4) + "C");

                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    queue.add(request);

                }
            }
        });


    }
}