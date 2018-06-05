package com.example.psaimadhav.flags;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    Button bt;
    TextView wrongattempts;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        bt=(Button)findViewById(R.id.button9);
        wrongattempts=(TextView) findViewById(R.id.textView12);
        Bundle extras= getIntent().getExtras();
        if(extras!= null) {
            count=extras.getInt("count");
        }
        wrongattempts.setText(String.valueOf(count));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                setResult(0,intent);
                finish();
            }
        });
    }
}
