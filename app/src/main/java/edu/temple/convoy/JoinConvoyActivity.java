package edu.temple.convoy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class JoinConvoyActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_convoy);

        //class empty for now
    }

    public void backClick(View view) {
        finish();
    }
}