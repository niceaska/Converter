package ru.niceaska.converter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class ConvertionActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertion);

        Intent intent = getIntent();
        EditText editText = findViewById(R.id.convertFrom);
        Value val = (Value) intent.getSerializableExtra("test");
        editText.setText(val.units.get(0).toString());
    }


}
