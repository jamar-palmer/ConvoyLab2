package edu.temple.convoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText pw;
    EditText username;
    RequestQueue requestQueue;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pw = findViewById(R.id.editPass);
        username = findViewById(R.id.editUsername);
        textView = findViewById(R.id.textView4);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void makeAccount(View view) {

        Intent launchIntent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(launchIntent);

    }

    public void beginConvoy(View view) {

        String login = "https://kamorris.com/lab/convoy/account.php";
        StringRequest strRequest = new StringRequest(Request.Method.POST, login,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                        if(response.contains("SUCCESS")){

                            String[] split = response.split(":");
                            String[] again = split[2].split("\"");
                            String session= again[1];

                            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("user",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("session",session);
                            editor.putString("username",username.getText().toString());
                            editor.apply();


                            Intent launchIntent = new Intent(MainActivity.this, ConvoyActivity.class);
                            startActivity(launchIntent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Incorrect Login Information", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "LOGIN");
                params.put("username", username.getText().toString());
                params.put("password",pw.getText().toString());
                return params;
            }
        };

        requestQueue.add(strRequest);
    }
}