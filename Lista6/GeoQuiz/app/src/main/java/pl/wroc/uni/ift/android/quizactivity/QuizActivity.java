package pl.wroc.uni.ift.android.quizactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    // Key for questions array to be stored in bundle;
    private static final String KEY_QUESTIONS = "questions";

    private static final int CHEAT_REQEST_CODE = 0;


    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mBankButton;

    private Button mCheatButton;
    TextView mApilevel;
    TextView mScoreview;
    private int mScore = 0;
    private int mToken = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private QuestionBank mQuestionsBank = QuestionBank.get(this);

    //  private int[] mCorrectAnswer = new int[mQuestionsBank.length];


    //    Bundles are generally used for passing data between various Android activities.
    //    It depends on you what type of values you want to pass, but bundles can hold all
    //    types of values and pass them to the new activity.
    //    see: https://stackoverflow.com/questions/4999991/what-is-a-bundle-in-an-android-application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");

        setTitle(R.string.app_name);
        // inflating view objects
        setContentView(R.layout.activity_quiz);

        // check for saved data
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("score");
            mToken = savedInstanceState.getInt("token");
            //  mCorrectAnswer = savedInstanceState.getIntArray("correct");

            // here in addition we are restoring our Question array;
            // getParcelableArray returns object of type Parcelable[]
            // since our Question is implementing this interface (Parcelable)
            // we are allowed to cast the Parcelable[] to desired type which
            // is the Question[] here.
            // mQuestionsBank = (Question []) savedInstanceState.getParcelableArray(KEY_QUESTIONS);
            // sanity check
            if (mQuestionsBank == null)
            {
                Log.e(TAG, "Question bank array was not correctly returned from Bundle");

            } else {
                Log.i(TAG, "Question bank array was correctly returned from Bundle");
            }

        }
// przycisk
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new QuestionAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mBankButton = (Button) findViewById(R.id.button_bank);
        mBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //boolean currentAnswer = mQuestionsBank.getQuestion(mCurrentIndex).isAnswerTrue();
                Intent intent = new Intent(QuizActivity.this, QuestionListActivity.class);

                startActivity(intent);
            }
        });
        mCheatButton = (Button) findViewById(R.id.button_cheat);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean currentAnswer = mQuestionsBank.getQuestion(mPager.getCurrentItem()).isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, currentAnswer);

//
//                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
//                boolean currentAnswer = mQuestionsBank[mCurrentIndex].isAnswerTrue();
//                intent.putExtra("answer", currentAnswer);

                startActivityForResult(intent, CHEAT_REQEST_CODE);
            }
        });






        mApilevel = (TextView) findViewById(R.id.APILEVEL);
        mApilevel.setText(" Android Version : " + Build.VERSION.RELEASE + " and API Level : " + Build.VERSION.SDK);
        mScoreview = (TextView) findViewById(R.id.score_view);
        mScoreview.setText(" Score : " + mScore);
        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == CHEAT_REQEST_CODE) {
            if (data != null)
            {
                boolean answerWasShown = CheatActivity.wasAnswerShown(data);
                if (answerWasShown) {
                    mToken = mToken - 1;
                    if (mToken == 0) {
                        mCheatButton.setEnabled(false);
                    }
                    String mystring = String.valueOf(mToken) + "/3" +" Tokeny!";
                    Toast.makeText(this,
                            mystring,
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("token",mToken);
        savedInstanceState.putInt("score", mScore);
        //   savedInstanceState.putIntArray("correct", mCorrectAnswer);
        super.onSaveInstanceState(savedInstanceState);

        //we still have to store current index to correctly reconstruct state of our app


        // because Question is implementing Parcelable interface
        // we are able to store array in Bundle
        //   savedInstanceState.putParcelableArray(KEY_QUESTIONS, mQuestionsBank);
    }

    private void updateQuestion() {
        if (mToken == 0) {
            mCheatButton.setEnabled(false);
        }

        //    DoneAnswers();
    }


    private class QuestionAdapter extends FragmentStatePagerAdapter {
        public QuestionAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            QAFragment mFrag = new QAFragment();
            mFrag.mQuestion = mQuestionsBank.getQuestion(position);
            return mFrag;
        }

        @Override
        public int getCount() {
            return mQuestionsBank.size();
        }
    }
}
