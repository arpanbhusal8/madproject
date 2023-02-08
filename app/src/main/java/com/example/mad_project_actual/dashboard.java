package com.example.mad_project_actual;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class dashboard extends AppCompatActivity {
    String sen_mail, sender_pass, receiver_mail,send_mail;
    double latitude, longitude;
    int time;
    private FusedLocationProviderClient fusedLocationClient;
    private static final String TAG = "Dashboard";
    Button signout,help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        Intent intent = getIntent();
        sen_mail = intent.getStringExtra("send_mail");
        sen_mail = sen_mail + "@gmail.com";
        sender_pass = intent.getStringExtra("send_pass");
        send_mail=sen_mail;
        receiver_mail = intent.getStringExtra("receiver_mail");
        int time = Integer.parseInt(intent.getStringExtra("time"));

        signout=findViewById(R.id.signout);
        help=findViewById(R.id.help);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    send_help(receiver_mail,latitude,longitude,send_mail);
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });






        Log.d(TAG, "onCreate: " + sen_mail + " " + receiver_mail);
        // App should ask for location and save latitude and longitude
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            Log.d(TAG, "latandlong " + longitude + " " + latitude);
                        }
                    }
                });


        Intent backgroundServiceIntent = new Intent(this, background.class);
        backgroundServiceIntent.putExtra("receiver_mail", receiver_mail);
        backgroundServiceIntent.putExtra("send_mail", send_mail);
        backgroundServiceIntent.putExtra("time", time);
        startService(backgroundServiceIntent);









        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "latandlong"+longitude +" "+latitude);
                    sendmail(receiver_mail,latitude,longitude,send_mail);
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, time * 60 * 1000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }














    public void sendmail(String receiver_mail, double latitude, double longitude, String send_mail) throws MessagingException, IOException
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Log.d(TAG, "emailInsideSendMail"+longitude +" "+latitude);
                MediaType mediaType = MediaType.parse("application/json");
//                String value = "{\r\n    \"personalizations\": [\r\n        {\r\n            \"to\": [\r\n                {\r\n                    \"email\": \"" + receiver_mail + "\"\r\n                }\r\n            ],\r\n            \"subject\": \"Latitude: " + latitude + " Longitude: " + longitude + "\"\r\n        }\r\n    ],\r\n    \"from\": {\r\n        \"email\": \"support@ilocate.com\"\r\n    },\r\n    \"content\": [\r\n        {\r\n            \"type\": \"text/plain\",\r\n            \"value\": \"Latitude: " + latitude + " Longitude: " + longitude + "\"\r\n        }\r\n    ]\r\n}";

                String value = "{\r\n    \"personalizations\": [\r\n        {\r\n            \"to\": [\r\n                {\r\n                    \"email\": \"" + receiver_mail + "\"\r\n                }\r\n            ],\r\n            \"subject\": \"Latitude: " + latitude + " Longitude: " + longitude + "\"\r\n        }\r\n    ],\r\n    \"from\": {\r\n        \"email\": \"" + send_mail + "\"\r\n    },\r\n    \"content\": [\r\n        {\r\n            \"type\": \"text/plain\",\r\n            \"value\": \"Latitude: " + latitude + " Longitude: " + longitude + "\"\r\n        }\r\n    ]\r\n}";


//                RequestBody body = RequestBody.create(mediaType, value.toString());
//                Request request = new Request.Builder()
//                        .url("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send")
//                        .post(body)
//                        .addHeader("content-type", "application/json")
//                        .addHeader("X-RapidAPI-Key", "70b5a5acccmshe324b84af92c9f7p184092jsn5d739a0d1dbf")
//                        .addHeader("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
//                        .build();aedens code





                RequestBody body = RequestBody.create(mediaType, value);
                Request request = new Request.Builder()
                        .url("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send")
                        .post(body)
                        .addHeader("content-type", "application/json")
                        .addHeader("X-RapidAPI-Key", "21cefa15ebmsh823b4049ae0ce0fp128b23jsn507d698c6443")
                        .addHeader("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
                        .build();


                try {
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "sendmail: response: " + response.code());

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "sendmail: error sending email", e);

                }


//                try {
//                    Response response = client.newCall(request).execute();
//                    Log.d(TAG, "sendmail: response: " + response);
//                    int responseCode = response.code();
//                    Log.d(TAG, "sendmail: response status: " + responseCode);
//                } catch (Exception e) {
//                    Log.e(TAG, "sendmail: error sending email", e);
//                }aedens code
            }


        }).start();
    }

    void send_help(String receiver_mail,double latitude,double longitude,String send_mail) throws MessagingException, IOException
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Log.d(TAG, "emailInsideSendMail"+longitude +" "+latitude);
                MediaType mediaType = MediaType.parse("application/json");
//                String value = "{\r\n    \"personalizations\": [\r\n        {\r\n            \"to\": [\r\n                {\r\n                    \"email\": \"" + receiver_mail + "\"\r\n                }\r\n            ],\r\n            \"subject\": \"Latitude: " + latitude + " Longitude: " + longitude + "\"\r\n        }\r\n    ],\r\n    \"from\": {\r\n        \"email\": \"support@ilocate.com\"\r\n    },\r\n    \"content\": [\r\n        {\r\n            \"type\": \"text/plain\",\r\n            \"value\": \"Latitude: " + latitude + " Longitude: " + longitude + "\"\r\n        }\r\n    ]\r\n}";

                String value = "{\r\n    \"personalizations\": [\r\n        {\r\n            \"to\": [\r\n                {\r\n                    \"email\": \"" + receiver_mail + "\"\r\n                }\r\n            ],\r\n            \"subject\": \"HELP!! I AM IN DANGER,MY LOCATION IS GIVEN BELOW!\"\r\n        }\r\n    ],\r\n    \"from\": {\r\n        \"email\": \"" + send_mail + "\"\r\n    },\r\n    \"content\": [\r\n        {\r\n            \"type\": \"text/plain\",\r\n            \"value\": \"Latitude: " + latitude + " Longitude: " + longitude + "\"\r\n        }\r\n    ]\r\n}";


//                RequestBody body = RequestBody.create(mediaType, value.toString());
//                Request request = new Request.Builder()
//                        .url("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send")
//                        .post(body)
//                        .addHeader("content-type", "application/json")
//                        .addHeader("X-RapidAPI-Key", "70b5a5acccmshe324b84af92c9f7p184092jsn5d739a0d1dbf")
//                        .addHeader("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
//                        .build();
//
//
//
//                try {
//                    Response response = client.newCall(request).execute();
//                    Log.d(TAG, "sendmail: response: " + response);
//                    int responseCode = response.code();
//                    Log.d(TAG, "sendmail: response status: " + responseCode);
//                } catch (Exception e) {
//                    Log.e(TAG, "sendmail: error sending email", e);
//                }
//            } aedens code




                RequestBody body = RequestBody.create(mediaType, value);
                Request request = new Request.Builder()
                        .url("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send")
                        .post(body)
                        .addHeader("content-type", "application/json")
                        .addHeader("X-RapidAPI-Key", "21cefa15ebmsh823b4049ae0ce0fp128b23jsn507d698c6443")
                        .addHeader("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
                        .build();


                try {
                    Response response = client.newCall(request).execute();
                    Log.d(TAG, "sendmail: response: " + response.code());

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "sendmail: error sending email", e);

                }}


        }).start();
    }






}




