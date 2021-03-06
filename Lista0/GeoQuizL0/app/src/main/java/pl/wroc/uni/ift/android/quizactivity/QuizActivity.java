package pl.wroc.uni.ift.android.quizactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {


    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    int mCurrentIndex = 0;

    private Question
        mQuestionBank[] =
        {
            new Question(R.string.question_stolica_mazur, true),
            new Question(R.string.question_stolica_dolnego_slaska, false),
            new Question(R.string.question_wisla, true)
        };


    private TextView mQuestionTextView;


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
                        if (mQuestionBank[mCurrentIndex].isAnswerTrue() == true){
                        Toast.makeText(QuizActivity.this,
                                "DOBRZE!",
                                Toast.LENGTH_LONG).show();}
                        else {
                            Toast.makeText(QuizActivity.this,
                                    "ŹLE",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCurrentIndex += 1;
                        if (mCurrentIndex == mQuestionBank.length) {
                            mCurrentIndex = 0;
                        }
                        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getmResourceId());
                    }
                });


        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestionBank[mCurrentIndex].isAnswerTrue() == false){
                    Toast.makeText(QuizActivity.this,
                            "DOBRZE!",
                            Toast.LENGTH_LONG).show();}
                else {
                    Toast.makeText(QuizActivity.this,
                            "ŹLE",
                            Toast.LENGTH_LONG).show();
                }


            }
        });



    }
}
