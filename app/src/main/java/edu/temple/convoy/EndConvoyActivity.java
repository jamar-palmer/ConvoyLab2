package edu.temple.convoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class EndConvoyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_convoy);
    }

    public void confirmClick(View view) {
        SharedPreferences settings = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("end","1");
        editor.apply();
        finish();
    }

    public void cancelClick(View view) {
        finish();
    }
}