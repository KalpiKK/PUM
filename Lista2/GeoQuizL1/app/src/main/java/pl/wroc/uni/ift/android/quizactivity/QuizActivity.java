package pl.wroc.uni.ift.android.quizactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {


    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mBackButton;

    private TextView mQuestionTextView;


    private Question[] mQuestionsBank = new Question[]{
            new Question(R.string.question_stolica_polski, true),
            new Question(R.string.question_stolica_dolnego_slaska, false),
            new Question(R.string.question_sniezka, true),
            new Question(R.string.question_wisla, true)
    };
    private int[] mDoneAnswer = new int[mQuestionsBank.length];
    private int mCurrentIndex = 0;

    private int ScoreAll=0;
    private int Score = 0;
    //    Bundles are generally used for passing data between various Android activities.
    //    It depends on you what type of values you want to pass, but bundles can hold all
    //    types of values and pass them to the new activity.
    //    see: https://stackoverflow.com/questions/4999991/what-is-a-bundle-in-an-android-application
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        // inflating view objects
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkAnswer(true);
                    }
                }
        );

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;

                updateQuestion();
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                updateQuestion();
            }
        });

        mBackButton = (ImageButton) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoneAnswers();
                if (mCurrentIndex == 0){
                    mCurrentIndex = mQuestionsBank.length;
                }
                mCurrentIndex = (mCurrentIndex - 1);
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        DoneAnswers();

    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();

        int toastMessageId = 0;

        if (userPressedTrue == answerIsTrue) {
            toastMessageId = R.string.correct_toast;
            mDoneAnswer[mCurrentIndex] = 1;
            Score = Score + 1;
        } else {
            toastMessageId = R.string.incorrect_toast;
            mDoneAnswer[mCurrentIndex] = 2;
        }

        DoneAnswers();

        Toast toast = Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 200);
        toast.show();
        AllSc();
    }

    private void DoneAnswers() {
        for (int x = 0; x < mQuestionsBank.length; x++) {
            if (mDoneAnswer[mCurrentIndex] == 1 || mDoneAnswer[mCurrentIndex] == 2) {
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
            } else {
                mTrueButton.setEnabled(true);
                mFalseButton.setEnabled(true);
            }
        }
    }
    private void AllSc(){
        int toastMessageId = 0;
        for (int i = 0; i < mQuestionsBank.length; i++) {
            if (mDoneAnswer[mCurrentIndex] == 1 || mDoneAnswer[mCurrentIndex] == 2) {
                ScoreAll = ScoreAll + 1;
            }
        }

        if (ScoreAll == 16) {
            String mystring = String.valueOf(Score) + "/4" +" Skończyleś!";
            Toast toast = Toast.makeText(this, mystring, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 200);
            toast.show();
        }
    }
}
