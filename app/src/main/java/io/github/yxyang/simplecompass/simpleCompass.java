package io.github.yxyang.simplecompass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class simpleCompass extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_compass);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);

        TextView xView = (TextView) findViewById(R.id.xAxis);
        xView.setTextSize(40);

        TextView yView = (TextView) findViewById(R.id.yAxis);
        yView.setTextSize(40);

        TextView zView = (TextView) findViewById(R.id.zAxis);
        zView.setTextSize(40);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float xAxis = event.values[0];
        float yAxis = event.values[1];
        float zAxis = event.values[2];

        TextView xView = (TextView) findViewById(R.id.xAxis);
        xView.setText("xAxis: " + String.format("%4.5f", xAxis));

        TextView yView = (TextView) findViewById(R.id.yAxis);
        yView.setText("yAxis: " + String.format("%4.5f", yAxis));

        TextView zView = (TextView) findViewById(R.id.zAxis);
        zView.setText("zAxis: " + String.format("%4.5f", zAxis));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
