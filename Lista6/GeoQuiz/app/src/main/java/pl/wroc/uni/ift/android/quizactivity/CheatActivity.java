package pl.wroc.uni.ift.android.quizactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private final static String EXTRA_KEY_ANSWER = "Answer";
    private final static String KEY_QUESTION = "QUESTION";
    private final static String EXTRA_KEY_SHOWN = "Shown";
    TextView mTextViewAnswer;
    Button mButtonShow;
    TextView mTextCheat;
    boolean mAnswer;

    boolean mCheat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswer = getIntent().getBooleanExtra(EXTRA_KEY_ANSWER,false);
        mTextViewAnswer = (TextView) findViewById(R.id.text_view_answer);
        mButtonShow = (Button) findViewById(R.id.button_show_answer);
        mTextCheat = (TextView) findViewById(R.id.button_cheat);
        if (savedInstanceState != null) {
            mCheat = savedInstanceState.getBoolean("cheeat");
            if (mCheat) {

                if (mAnswer) {
                    mTextViewAnswer.setText("Prawda");
                } else {
                    mTextViewAnswer.setText("Fałsz");
                }
            }
        }
        mButtonShow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (mAnswer) {
                    mTextViewAnswer.setText("Prawda");
                } else {
                    mTextViewAnswer.setText("Fałsz");
                }
                setAnswerShown(true);
            }
        });

        setAnswerShown(mCheat);

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
       savedInstanceState.putBoolean("cheeat", mCheat);
    }

    public static boolean wasAnswerShown(Intent data)
    {
        return data.getBooleanExtra(EXTRA_KEY_SHOWN, false);
    }

    public static Intent newIntent(Context context, boolean answerIsTrue)
    {
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(EXTRA_KEY_ANSWER, answerIsTrue);
        return intent;


    }

    private void setAnswerShown (boolean isAnswerShown) {
        mCheat = isAnswerShown;
        Intent data = new Intent();
        data.putExtra(EXTRA_KEY_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);


    }





}
