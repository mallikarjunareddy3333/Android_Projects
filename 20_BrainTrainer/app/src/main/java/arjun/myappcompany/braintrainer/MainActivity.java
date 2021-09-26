package arjun.myappcompany.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView resultText;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    int locationCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score = 0;
    int numberOfQuestions = 0;

    public void chooseAnswer(View view) {
        Log.i("Tag:", view.getTag().toString());
        int selectedAnswer = Integer.parseInt(view.getTag().toString());
        if ( selectedAnswer == locationCorrectAnswer) {
            resultText.setText("Correct!");
            score++;
        } else {
            resultText.setText("Wrong :(");
        }

        numberOfQuestions++;

        scoreTextView.setText(score+"/"+numberOfQuestions);

        newQuestion();

    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(score+"/"+numberOfQuestions);
        resultText.setText("");

        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultText.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void newQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for ( int i=0; i<4; i++) {
            if ( i == locationCorrectAnswer ) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == (a+b)) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultTextView);
        goButton = findViewById(R.id.goButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        playAgain(findViewById(R.id.timerTextView));

    }
}