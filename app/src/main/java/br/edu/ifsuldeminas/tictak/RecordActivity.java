package br.edu.ifsuldeminas.tictak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecordActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        bottomNavigationView = findViewById(R.id.bottomNavigationViewRecord);


        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.playPage:
                    Intent intent = new Intent(RecordActivity.this, AddPlayers.class);
                    startActivity(intent);
                    break;
                case R.id.recordPage:
                    break;
            }
            return true;

        });
    }
}