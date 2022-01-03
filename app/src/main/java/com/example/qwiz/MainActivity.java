package com.example.qwiz;

import static com.example.qwiz.R.id.barrier;
import static com.example.qwiz.R.id.true_buttoon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button trueButton;
    private Button falseButton;
    private TextView questionTextView;
    private ImageButton prevButton;
    private ImageButton nextButton;

    private int currentQuestionIndex = 0;


    private Question[] questionBank = new Question[]{
            new Question(R.string.Question_ocean, false),
            new Question(R.string.Question_temp, true),
            new Question(R.string.Question_Spider, false),
            new Question(R.string.Question_electron, false),
            new Question(R.string.Question_physics, true)
    };
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(true_buttoon);
        questionTextView = findViewById(R.id.answer_text_view);
        prevButton = findViewById(R.id.prev_button);
        nextButton = findViewById(R.id.next_button);


        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.true_buttoon:
                checkAnswer(true);
                break;


            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex+1)% questionBank.length;
                updateQuestion();
                break;

            case R.id.prev_button:
                if(currentQuestionIndex >0){
                currentQuestionIndex = (currentQuestionIndex-1)% questionBank.length;
                updateQuestion();
                }



        }

    }
    private void updateQuestion(){
        Log.d("current", "onclick"+ currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());

    }

    private void checkAnswer(boolean userChosenCorrect){
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();

        int toastMessageId = 0;
        if(userChosenCorrect == answerIsTrue){
            toastMessageId = R.string.correct_answer;
        }
        else {
            toastMessageId = R.string.wrong_answer;
        }
        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();

    }
}