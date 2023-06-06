package br.edu.ifsuldeminas.tictak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsuldeminas.tictak.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final List<int[]> combinationList = new ArrayList<>();
    private final int[] boxPositions = new int[9]; //9 zeros
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;

    int PontuationPlayer1 = 0;
    int PontuationPlayer2 = 0;

    TextView pontuationPlayer1, pontuationPlayer2;

    ImageView backHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeCombinationList();

        String playerOneName = getIntent().getStringExtra("playerOne");
        String playerTwoName = getIntent().getStringExtra("playerTwo");



        binding.playerOneName.setText(playerOneName);
        binding.playerTwoName.setText(playerTwoName);

        setupOnClickListeners();

        backHome =findViewById(R.id.backHome);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPlayers.class);
                startActivity(intent);
            }
        });
    }

    private void initializeCombinationList() {
        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});
    }

    private void setupOnClickListeners() {
        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(binding.image1);
        imageViews.add(binding.image2);
        imageViews.add(binding.image3);
        imageViews.add(binding.image4);
        imageViews.add(binding.image5);
        imageViews.add(binding.image6);
        imageViews.add(binding.image7);
        imageViews.add(binding.image8);
        imageViews.add(binding.image9);

        for (int i = 0; i < imageViews.size(); i++) {
            ImageView imageView = imageViews.get(i);
            final int selectedBoxPosition = i;

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isBoxSelectable(selectedBoxPosition)) {
                        performAction((ImageView) view, selectedBoxPosition);
                    }
                }
            });
        }
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            if (checkResults()) {
                PontuationPlayer1++;
                if(PontuationPlayer1 >= 3){
                    showResultDialog(binding.playerOneName.getText().toString() + " é o ganhador do GAME");
                    PontuationPlayer1 = 0;
                    PontuationPlayer2 = 0;
                }else {
                    showResultDialog(binding.playerOneName.getText().toString() + " é o ganhador dessa rodada!");
                }
                } else if (totalSelectedBoxes == 9) {
                showResultDialog("Empate");
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            if (checkResults()) {
                PontuationPlayer2++;
                if(PontuationPlayer2 >= 3){
                    showResultDialog(binding.playerTwoName.getText().toString() + " é o ganhador do GAME");
                    PontuationPlayer1 = 0;
                    PontuationPlayer2 = 0;
                }
                else {
                    showResultDialog(binding.playerTwoName.getText().toString() + " é o ganhador dessa rodada!");
                }
            } else if (totalSelectedBoxes == 9) {
                showResultDialog("Empate");
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }


        pontuationPlayer1 = findViewById(R.id.pontuationPlayer1);
        pontuationPlayer2 = findViewById(R.id.pontuationPlayer2);


        pontuationPlayer1.setText(String.valueOf(PontuationPlayer1));
        pontuationPlayer2.setText(String.valueOf(PontuationPlayer2));
    }

    private void showResultDialog(String message) {
        ResultDialog resultDialog = new ResultDialog(MainActivity.this, message, MainActivity.this);
        resultDialog.setCancelable(false);
        resultDialog.show();
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border);
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResults() {
        for (int[] combination : combinationList) {
            if (boxPositions[combination[0]] == playerTurn &&
                    boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    public void restartMatch() {
        for (int i = 0; i < boxPositions.length; i++) {
            boxPositions[i] = 0;
        }

        playerTurn = 1;
        totalSelectedBoxes = 1;

        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(binding.image1);
        imageViews.add(binding.image2);
        imageViews.add(binding.image3);
        imageViews.add(binding.image4);
        imageViews.add(binding.image5);
        imageViews.add(binding.image6);
        imageViews.add(binding.image7);
        imageViews.add(binding.image8);
        imageViews.add(binding.image9);

        for (ImageView imageView : imageViews) {
            imageView.setImageResource(R.drawable.white_box);
        }
    }
}