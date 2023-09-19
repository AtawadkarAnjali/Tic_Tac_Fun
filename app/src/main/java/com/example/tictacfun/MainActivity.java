package com.example.tictacfun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {
        boolean gameActive = true;
        //player's representaion
        //0 - X
        // 1 - O
    int activePlayer = 0;
    int[] gameState= {2, 2, 2, 2, 2, 2, 2, 2, 2};
        //game states : -
        //0 = X
        //1 = O
        //2 = NULL

    int [][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},     //horizontal winners position.
                            {0,3,6},  {1,4,7}, {2,5,8},     //vertical winners position.
                            {0,4,8},  {2,4,6}};             //cross winners position.
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage] == 2 ) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationX(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.f);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
            } else {
                img.setImageResource(R.drawable.d);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");
            }
            img.animate().translationXBy(1000f).setDuration(300);
        }
        //check if any player has won..
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {

                // who wins ? = X or O
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "Congrats, Player's X has won the game !! ";
                } else {
                    winnerStr = "Congrats, Player's O has won the game !! ";
                }
                //update status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }

        }
    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i = 0;  i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}