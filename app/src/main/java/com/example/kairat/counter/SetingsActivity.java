package com.example.kairat.counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetingsActivity extends AppCompatActivity implements View.OnClickListener{
    Button save;
    EditText max_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setings);
        save = (Button) findViewById(R.id.save_button);
        max_num = (EditText) findViewById(R.id.max_number);
        save.setOnClickListener(this);
        max_num.setText(String.valueOf(10));

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.save_button:
                if (max_num.getText().length() != 0){
                    String m = max_num.getText().toString();
                    intent.putExtra("max_number", Integer.valueOf(m));
                    break;
                }

        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
