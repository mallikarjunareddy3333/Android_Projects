package arjun.myappcompany.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0: yellow, 1: red, 2: empty

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, { 0, 3, 6}, {1, 4, 7}, { 2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int taggedCounter = Integer.parseInt( counter.getTag().toString());

        if (gamestate[taggedCounter] == 2 && gameActive) {

            gamestate[taggedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(200);

            for (int[] winningPosition : winningPositions) {

                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]] && gamestate[winningPosition[0]] != 2) {

                    gameActive = false;

                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgain = (Button) findViewById(R.id.playAgain);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" has won!");

                    playAgain.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }

            }

        }
    }

    public void playAgain(View view) {

        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout myGridlayout = (GridLayout) findViewById(R.id.myGridLayout);

        for(int i = 0; i < myGridlayout.getChildCount(); i++) {
            ImageView counter = (ImageView) myGridlayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for( int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }

        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}