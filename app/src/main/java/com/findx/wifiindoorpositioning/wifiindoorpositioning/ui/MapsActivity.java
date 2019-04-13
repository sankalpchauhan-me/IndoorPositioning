package com.findx.wifiindoorpositioning.wifiindoorpositioning.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.findx.wifiindoorpositioning.wifiindoorpositioning.R;
import com.findx.wifiindoorpositioning.wifiindoorpositioning.model.MyLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    String TAG = "MapsActivity";

    private GoogleMap mMap;
    double Lat, Lon;

    GoogleApiClient mGoogleApiClient;

    private SensorManager sensorManager;
    Sensor accelerometer;
    Sensor gyroscope;
    private SensorEventListener gyroscopesensorEventListener;
    private SensorEventListener accelerometersensorEventListner;
    private SensorEventListener stepListner;
    Sensor stepCounter;
    static int steps = 0;

    private static final long INTERVAL = 1000; //1 minute
    private static final long FASTEST_INTERVAL = 1000; // 1 minute

    LocationRequest mLocationRequest;
    String mLastUpdateTime;


    private FusedLocationProviderClient mFusedLocationClient;

    private LocationCallback locationCallback;

    Bitmap marker;

    TextView acelX,acelY,acelZ,gyroX,gyroY,gyroZ,stepsTV;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        createLocationRequest();

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        setContentView(R.layout.activity_maps);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        marker =getBitmapFromVectorDrawable(this,R.drawable.ic_markermap);

        acelX = findViewById(R.id.acelX);
        acelY = findViewById(R.id.acelY);
        acelZ = findViewById(R.id.acelZ);
        gyroX = findViewById(R.id.gyroX);
        gyroY = findViewById(R.id.gyroY);
        gyroZ = findViewById(R.id.gyroZ);
        stepsTV = findViewById(R.id.steps);

// ...

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                // ...
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(MapsActivity.this,
                                400);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //mMap.getUiSettings().setZoomControlsEnabled(true);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        final BitmapDescriptor image = BitmapDescriptorFactory.fromResource(R.drawable.roomplaceholder);
//        mMap = googleMap;
//        final LatLngBounds springboardBounds = new LatLngBounds(
//                new LatLng(28.589378, 77.314326),       // South west corner
//                new LatLng(28.589780, 77.314568));      // North east corner
//
//        // Add a marker in Sydney and move the camera
//
//        LatLng sydney = new LatLng(Lat, Lon);
//        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
//                sydney, 10);
//        mMap.animateCamera(location);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        GroundOverlayOptions springboard = new GroundOverlayOptions()
//                .image(image)
//                .positionFromBounds(springboardBounds);
//        mMap.addGroundOverlay(springboard);

        mMap = googleMap;


        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    // ...
                    // addMarker(location.getLatitude(), location.getLongitude(), location.getTime());

                    // Add a marker in Sydney and move the camera

                    if(mMap!=null) {
                        BitmapDescriptor bitmapDescriptorFactory = BitmapDescriptorFactory.fromBitmap(marker);

                        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                        CameraUpdate locationcamera = CameraUpdateFactory.newLatLngZoom(
                                sydney, 50);
                        mMap.animateCamera(locationcamera);

                        mMap.addMarker(new MarkerOptions().position(sydney).icon(bitmapDescriptorFactory));
                    }

                    Log.e("CHEKKER6", location.getLatitude() + " " + location.getLongitude());
                }
            }
        };

        startLocationUpdates();


         initializeSensors();
    }

//        mMap=googleMap;
//        addMarker();
//        mMap.getUiSettings().setZoomControlsEnabled(true);


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    void initializeSensors() {
        Log.e(TAG, "Initializing Sensor Service");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);


        if (gyroscope == null || accelerometer == null || stepCounter == null) {
            Toast.makeText(this, "Calibrate Sensor", Toast.LENGTH_LONG).show();
        }

        gyroscopesensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.e(TAG, "GYROSCOPE: X: " + (double) event.values[0] + " Y: " + (double) event.values[1] + " Z: " + (double) event.values[2] + "\n");
                gyroX.setText(String.format("%.4f",(double)event.values[0]));
                gyroY.setText(String.format("%.4f",(double)event.values[0]));
                gyroZ.setText(String.format("%.4f",(double)event.values[0]));


            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        Log.e(TAG, "Gyroscope Service Ready");
        sensorManager.registerListener(gyroscopesensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);

        accelerometersensorEventListner = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.e(TAG, "Accelrometer: X: " + (double) event.values[0] + " Y: " + (double) event.values[1] + " Z: " + (double) event.values[2] + "\n");
                acelX.setText(String.format("%.4f",(double)event.values[0]));
                acelY.setText(String.format("%.4f",(double)event.values[1]));
                acelZ.setText(String.format("%.4f",(double)event.values[2]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(accelerometersensorEventListner, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        stepListner = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.e(TAG, "StepCounter: X: " + (int) event.values[0]);
                stepsTV.setText(String.valueOf((int)event.values[0]));

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(stepListner, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);

    }

//    private void addMarker(double Lat, double Lon, long time) {
//        MarkerOptions options = new MarkerOptions();
//
//        // following four lines requires 'Google Maps Android API Utility Library'
//        // https://developers.google.com/maps/documentation/android/utility/
//        // I have used this to display the time as title for location markers
//        // you can safely comment the following four lines but for this info
//
////        IconGenerator iconFactory = new IconGenerator(this);
////        iconFactory.setStyle(IconGenerator.STYLE_PURPLE);
////        options.icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(mLastUpdateTime)));
////        options.anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());
//
//
//        LatLng currentLatLng = new LatLng(Lat, Lon);
//        options.position(currentLatLng);
//        Marker mapMarker = mMap.addMarker(options);
//        long atTime = time;
//        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date(atTime));
//        mapMarker.setTitle(mLastUpdateTime);
//        Log.d(TAG, "Marker added.............................");
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,
//                13));
//        Log.d(TAG, "Zoom done.............................");
//
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient != null && mFusedLocationClient != null) {
            requestLocationUpdates();
        } else {
            buildGoogleApiClient();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                locationCallback,
                null /* Looper */);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void requestLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(50); // two minute interval
        mLocationRequest.setFastestInterval(500);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper());
        }
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}

