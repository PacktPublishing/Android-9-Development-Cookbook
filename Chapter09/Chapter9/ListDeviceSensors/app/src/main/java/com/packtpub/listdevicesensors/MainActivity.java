package com.packtpub.listdevicesensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        List sensorList = new ArrayList<String>();

        List<Sensor> sensors = ((SensorManager) getSystemService(Context.SENSOR_SERVICE))
                .getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors ) {
            sensorList.add(sensor.getName());
        }
        ListAdapter sensorAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, sensorList);
        listView.setAdapter(sensorAdapter);

        SensorManager sensorManager =  ((SensorManager) getSystemService(Context.SENSOR_SERVICE));
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            //Sensor is available - do something here
        }
    }
}
