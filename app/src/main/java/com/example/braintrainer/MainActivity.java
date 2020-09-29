package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;


public class MainActivity extends AppCompatActivity
{
    //Variables
    final int MAX_TIME = 30; //30 seconds
    CountDownTimer countDownTimer;
    TextView timer;
    TextView score;
    TextView equation;
    TextView done;
    Button playAgain;
    GridLayout gridLayout;
    int firstNum;
    int secondNum;
    int numOfQuestions;
    int numOfCorrectAns;
    int correctAnsButton;
    boolean isGameOver;


    //Methods
    //answer checker after button is clicked
    public void checkAns( View v)
    {
        if ( !isGameOver)
        {
            if (Integer.parseInt(v.getTag().toString()) == correctAnsButton)
            {
                numOfCorrectAns++;
                done.setText( "CORRECT");
                done.setVisibility(View.VISIBLE);
            }
            else
            {
                done.setText( "INCORRECT");
                done.setVisibility(View.VISIBLE);
            }
            numOfQuestions++;
            updateScore();
            ranEquation();
            randomAns();
        }
    }

    //Update scores
    public void updateScore()
    {
        score.setText( numOfCorrectAns+ "/" + numOfQuestions);
    }

    //new game
    public void newGameStart()
    {
        isGameOver = false;

        //Making textview and button invisible
        done.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        //Setting the score
        numOfQuestions = 0;
        numOfCorrectAns = 0;
        updateScore();

        //Setting the timer countdown and textview
        timer = (TextView) findViewById(R.id.timer);
        timer.setText( MAX_TIME + "s");
        countDownTimer = new CountDownTimer( MAX_TIME * 1000 + 1000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timer.setText( (int) millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish()
            {
                done.setText("DONE!");
                done.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                isGameOver = true;
            }
        }.start();

        ranEquation();
        randomAns();

    }

    //button listener for the play again button
    public void newGame( View v )
    {
        newGameStart();
    }

    //Generated random answers
    public void randomAns()
    {

        //Get a random number between 0 and 3
        correctAnsButton = (int) ( Math.random() * 4 );

        //fill all buttons with random answers
        for (int i = 0; i < gridLayout.getChildCount(); i++)
        {
            Button child = (Button) gridLayout.getChildAt(i);
            if ( i == correctAnsButton)
            {
                child.setText( firstNum + secondNum + ""  );
            }
            else
            {
                int ran = (int) ( Math.random() * 20 ) + 1 ;
                while ( ran == firstNum + secondNum)
                {
                    ran = (int) ( Math.random() * 20 ) + 1;
                }

                child.setText(  ran + "" );
            }
        }

    }

    //Generates random question
    public void ranEquation()
    {
        firstNum = (int) ( Math.random() * 10 ) + 1; //get a random number between 1 and 10
        secondNum = (int) ( Math.random() * 10 ) + 1; //get a random number between 1 and 10
        equation.setText( firstNum + "+" + secondNum);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing
        score = (TextView) findViewById(R.id.score);
        equation = (TextView) findViewById(R.id.equation);
        gridLayout = (GridLayout) findViewById(R.id.choices);
        done = (TextView) findViewById(R.id.done);
        playAgain  = (Button) findViewById(R.id.playAgain);

        newGameStart();

    }
}
