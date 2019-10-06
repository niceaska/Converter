package ru.niceaska.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycleView();
    }


    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.value);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        ConverterValuesAdapter converterValuesAdapter = new ConverterValuesAdapter(Arrays.asList(getResources().getStringArray(R.array.units)), new IMyOnClickMethod() {
            @Override
            public void onItemClick(Value item) {
                Intent intent = new Intent(MainActivity.this, ConvertionActivity.class);
                intent.putExtra(getResources().getString(R.string.intent_flag), item);
                startActivity(intent);

            }
        });
        DividerItemDecoration deviderItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(deviderItemDecoration);
        recyclerView.setAdapter(converterValuesAdapter);

    }
}