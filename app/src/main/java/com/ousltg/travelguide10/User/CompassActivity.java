package com.ousltg.travelguide10.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ousltg.travelguide10.R;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private ImageView compassImageView; // Reference to the compass image view
    private TextView temperatureTextView; // Reference to the temperature text view
    private float currentDegree = 0f; // Current degree of rotation for the compass
    private SensorManager sensorManager; // Sensor manager to access device sensors
    private Sensor accelerometerSensor;  // Accelerometer sensor for detecting device motion
    private Sensor magnetometerSensor; // Magnetometer sensor for detecting magnetic field
    private Sensor temperatureSensor; // Temperature sensor for ambient temperature
    private float[] gravityValues = new float[3];  // Array to store accelerometer values
    private float[] magneticValues = new float[3]; // Array to store magnetometer values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        getSupportActionBar().setTitle("Travel Guide");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        compassImageView = findViewById(R.id.compass); // Initialize compass image view
        temperatureTextView = findViewById(R.id.textView14); // Initialize temperature text view

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); // Initialize sensor manager
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  // Get accelerometer sensor
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); // Get magnetometer sensor
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE); // Get temperature sensor
    }

    // Register sensor event listeners with appropriate delay
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister sensor event listeners
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Handle sensor events
        if (event.sensor == accelerometerSensor) {
            gravityValues = event.values.clone();
        } else if (event.sensor == magnetometerSensor) {
            magneticValues = event.values.clone();
        } else if (event.sensor == temperatureSensor) {
            float temperature = event.values[0];
            temperatureTextView.setText(temperature + " °C");
        }

        // Calculate device orientation based on gravity and magnetic field values
        float[] rotationMatrix = new float[9];
        boolean success = SensorManager.getRotationMatrix(rotationMatrix, null, gravityValues, magneticValues);

        // Create rotation animation for the compass image view
        if (success) {
            float[] orientationValues = new float[3];
            SensorManager.getOrientation(rotationMatrix, orientationValues);
            float azimuth = (float) Math.toDegrees(orientationValues[0]);
            RotateAnimation rotateAnimation = new RotateAnimation(
                    currentDegree, -azimuth, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(250);
            rotateAnimation.setFillAfter(true);
            compassImageView.startAnimation(rotateAnimation); // Start the rotation animation
            currentDegree = -azimuth; // Update the current degree with the
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this implementation
    }
}