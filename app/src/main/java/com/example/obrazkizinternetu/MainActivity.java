package com.example.obrazkizinternetu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");
        initList();
    }

    private void initList()
    {
        String iss_Station = getResources().getString(R.string.S_ISS_STATION);
        String moon = getResources().getString(R.string.S_MOON);
        String sun = getResources().getString(R.string.S_SUN);
        names.add(iss_Station);
        names.add(moon);
        names.add(sun);

        initRecyclerView();
    }

    private void initRecyclerView()
    {
        Log.d(TAG, "initRecyclerView: init recyclerView");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, names);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onISSClk(View v)
    {
        Intent iss = new Intent(this, ISSActivity.class);
        startActivity(iss);
    }

    public void onMonClk(View v)
    {
        Intent moon = new Intent(this, MoonActivity.class);
        startActivity(moon);
    }

    public void onSunClk(View v)
    {
        Intent sun = new Intent(this, SunActivity.class);
        startActivity(sun);
    }



}
