package com.norujin.android.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 for O, 1 for X, and 2 for empty cell
    int activePlayer = 0;
    int[][] winning_states = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int[] gameStates = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean gameActive = true;

    private Button playAgain;
    private TextView result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result= (TextView) findViewById(R.id.winnerTxtVId);
        playAgain= (Button) findViewById(R.id.playAgainBtnId);


        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                letsPlayAgain();
            }
        });
    }


    public void dropIn (View view){

        ImageView counter = (ImageView) view;

        int taggedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameStates[taggedCounter] == 2 && gameActive) {
            gameStates[taggedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winning_state : winning_states) {

                String winner = "";

                if (gameStates[winning_state[0]] == gameStates[winning_state[1]] && gameStates[winning_state[1]] == gameStates[winning_state[2]] && gameStates[winning_state[0]] != 2) {

                    if (activePlayer == 1) {

                        winner = "O has won";

                    } else {

                        winner = "X has won";

                    }

                    result.setVisibility(View.VISIBLE);
                    result.setText(winner);

                    playAgain.setVisibility(View.VISIBLE);

                    gameActive = false;

                }
            }
        }

    }


    public void letsPlayAgain(){

        playAgain.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i=0; i< gridLayout.getChildCount(); i++) {

            ImageView imageView = (ImageView) gridLayout.getChildAt(i);

            imageView.setImageDrawable(null);

        }

        for (int i=0; i < gameStates.length; i++){

            gameStates[i]=2;

        }

        activePlayer = 0;
        gameActive = true;

    }


}
