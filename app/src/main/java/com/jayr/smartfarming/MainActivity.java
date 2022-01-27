package com.jayr.smartfarming;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
TextView humidity, temprature, soilmoisture ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefHum = database.getReference("Smart_Green_Data/Humidity");

    DatabaseReference myRefSoil = database.getReference("Smart_Green_Data/Soil Moisture");

    DatabaseReference myRefTemp= database.getReference("Smart_Green_Data/Temperature");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        humidity = findViewById(R.id.humidity);
        temprature = findViewById(R.id.temperature);
        soilmoisture = findViewById(R.id.soilmositure);

        myRefHum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String value = snapshot.getValue().toString();

                for(DataSnapshot child: snapshot.getChildren()){
                    String ata = child.getValue().toString();
                    System.out.println("===== > "+ata);
                    humidity.setText(ata);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRefSoil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String value = snapshot.getValue().toString();
//                System.out.println("===== > "+value);
                for(DataSnapshot child: snapshot.getChildren()){
                    String ata = child.getValue().toString();
                    System.out.println("===== > "+ata);

                    soilmoisture.setText(ata);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        myRefTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String value = snapshot.getValue().toString();
//                System.out.println("===== > "+value);
                for(DataSnapshot child: snapshot.getChildren()){
                    String ata = child.getValue().toString();
                    System.out.println("===== > "+ata);

                    temprature.setText(ata);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}