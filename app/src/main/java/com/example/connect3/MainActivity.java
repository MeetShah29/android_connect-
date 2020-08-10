package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0=yellow 1=red
    int activePlayer = 0; //tells us who is playing
    //2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    ImageView counter;
    int[][] winningPositons = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    LinearLayout linearLayout;
    TextView textView;
    GridLayout gridView;
    boolean isGameActive=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.grid);
        linearLayout = findViewById(R.id.playAgain_layout);
        textView = findViewById(R.id.winnerMsg);

    }

    public void dropIn(View view) {
        counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && isGameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(200);
            for (int[] winningPositions : winningPositons) {
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] &&
                        gameState[winningPositions[1]] == gameState[winningPositions[2]] &&
                        gameState[winningPositions[0]] != 2) {
                    String winner = "RED";
                    if (gameState[winningPositions[0]] == 0) {
                        winner = "Yellow";
                    }
                    //Someone has won
                    isGameActive=false;
                    textView.setText(winner + " has Won!");
                    linearLayout.animate().rotation(3600);
                    linearLayout.setVisibility(View.VISIBLE);
                }else{
                    boolean gameIsOver=true;
                    for(int counterState:gameState){
                        if(counterState==2)
                            gameIsOver=false;
                        }
                        if(gameIsOver){
                            linearLayout.animate().rotation(3600).setDuration(1000);
                            textView.setText("It's a Draw");
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }

    public void playAgain(View view) {
        isGameActive=true;
        linearLayout.setVisibility(View.GONE);
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        for (int i = 0; i < gridView.getChildCount(); i++) {
            ((ImageView)gridView.getChildAt(i)).setImageResource(0);

        }
    }

}