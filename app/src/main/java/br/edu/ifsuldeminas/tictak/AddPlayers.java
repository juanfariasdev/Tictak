package br.edu.ifsuldeminas.tictak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.edu.ifsuldeminas.tictak.databinding.ActivityMainBinding;

public class AddPlayers extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        EditText playerOne = findViewById(R.id.playerOne);
        EditText playerTwo = findViewById(R.id.playerTwo);
        Button startGameButton = findViewById(R.id.startGameButton);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.playPage:
                    break;
                case R.id.recordPage:
                    Intent intent = new Intent(AddPlayers.this, RecordActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;

        });


        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getPlayerOneName = playerOne.getText().toString();
                String getPlayerTwoName = playerTwo.getText().toString();

                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    if(getPlayerOneName.isEmpty()){
                        Toast.makeText(AddPlayers.this, "Digite o nome do jogador 1", Toast.LENGTH_SHORT).show();
                    }
                    if(getPlayerTwoName.isEmpty()) {
                        Toast.makeText(AddPlayers.this, "Digite o nome do jogador 2", Toast.LENGTH_SHORT).show();
                    }
                    } else {
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });

    }
}