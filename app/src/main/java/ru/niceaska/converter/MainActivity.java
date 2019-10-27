package ru.niceaska.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements IConvertValueHolder {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_id, new ConvertionsListFragment())
                    .commit();
        }
    }


    @Override
    public void convertValue(Value value) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_id, ConvertValueFragment.newInstance(value))
                .addToBackStack(null)
                .commit();
    }
}