package ru.niceaska.converter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.Locale;

public class ConvertionActivity extends Activity {


    private ConverterHelper converterHelper;
    private EditText editTextFrom;
    private EditText editTextTo;
    boolean focusOnFrom = false;
    boolean focusOnTo = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertion);
        initSpinners();
    }

    private void initSpinners() {
        editTextFrom = findViewById(R.id.convertFrom);
        editTextTo = findViewById(R.id.convertTo);
        Spinner spinnerFrom = findViewById(R.id.spinner_from);
        Spinner spinnerTo = findViewById(R.id.spinner_to);


        Intent intent = getIntent();
        Value val = (Value) intent.getSerializableExtra(getResources().getString(R.string.intent_flag));

        if (val == null) return;

        converterHelper = new ConverterHelper(this, val.units);
        final List <String> spinner_values = converterHelper.generateValuesArray();
        spinnerFrom.setAdapter(
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, spinner_values)
        );
        spinnerTo.setAdapter(
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, spinner_values)
        );

        spinnerFrom.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                converterHelper.setPositionFrom(position);
                if (focusOnFrom) {
                    convertionSet(editTextFrom.getText().toString(), editTextTo, false);
                } else if (focusOnTo) {
                    convertionSet(editTextFrom.getText().toString(), editTextFrom, true);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTo.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                converterHelper.setPositionTo(position);
                if (focusOnFrom) {
                    convertionSet(editTextFrom.getText().toString(), editTextTo, false);
                } else if (focusOnTo) {
                    convertionSet(editTextFrom.getText().toString(), editTextFrom, true);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editTextFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                focusOnFrom = hasFocus;
            }
        });

        editTextTo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                focusOnTo = hasFocus;
            }
        });

        editTextTo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (focusOnTo) {
                    convertionSet(s.toString(), editTextFrom, true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (focusOnFrom) {
                    convertionSet(s.toString(), editTextTo, false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void convertionSet(String s, EditText editText, boolean reversePos) {
        try {
            double result = converterHelper.convert(Double.parseDouble(s), reversePos);
            editText.setText(String.format(Locale.US, "%.2f", result));
        } catch (NumberFormatException e) {
            editText.setText("");
        }
    }


}
